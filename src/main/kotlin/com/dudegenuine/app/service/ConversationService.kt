package com.dudegenuine.app.service

import com.dudegenuine.app.entity.MessageDto.Companion.TYPE_MESSAGE
import com.dudegenuine.app.entity.MessageDto.Companion.TYPE_TYPING
import com.dudegenuine.app.model.conversation.session.ConversationSession
import com.dudegenuine.app.model.message.MessageCreateRequest
import com.dudegenuine.app.repository.contract.IBlacklistRepository
import com.dudegenuine.app.repository.contract.IConversationRepository
import com.dudegenuine.app.repository.contract.IMessageRepository
import com.dudegenuine.app.repository.contract.IParticipantRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.InternalErrorException
import com.dudegenuine.app.service.contract.IConversationService
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ConversationService(
    private val converseRepository: IConversationRepository,
    private val participantRepository: IParticipantRepository,
    private val blacklistRepository: IBlacklistRepository,
    private val messageRepository: IMessageRepository): IConversationService {

    override suspend fun onJoinConversation(
        session: ConversationSession) = with(session) {
        try {
            if (blacklistRepository.isAccessBlocked(session.from to session.to))
                throw BadRequestException("sender was blocked.")
            val converseId = converseRepository.onSessionConnect(this)/*{ sender, recipient -> participantRepository.requireCreateParticipants(sender, recipient) }*/
            socket.incoming.consumeEach { frame ->
                if (frame is Frame.Text)
                    onBroadcastConversation(converseId, session, frame.readText())
            }
        } catch (e: BadRequestException){
            socket.close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, e.localizedMessage))
        } catch (e: Exception){
            socket.close(CloseReason(CloseReason.Codes.PROTOCOL_ERROR, e.localizedMessage))
        } finally {
            converseRepository.onSessionDisconnect(this)
        }
    }
    override fun pagedConversations(userId: String, pageAndSize: Pair<Long, Int>) =
        try { converseRepository.readConversations(userId, pageAndSize) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    override fun removeConversation(conversationId: String) =
        try { converseRepository.deleteConversation(conversationId) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    private suspend fun onBroadcastConversation(
        converseId: String, session: ConversationSession, payload: String) = with(converseRepository) {
        try {
            val (mConverseIdOrNull, mFrom, mTo) = session
            val message: MessageCreateRequest = Json.decodeFromString(payload)

            when (message.type) {
                TYPE_MESSAGE -> {
                    message
                        .copy(userId = mFrom, converseId = mConverseIdOrNull ?: converseId)
                        .let(messageRepository::createMessage)

                    onSendBroadcast(mFrom to mTo, payload)
                }
                TYPE_TYPING ->
                    onSendBroadcast(mFrom to mTo, payload, true)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            onSessionDisconnect(session)
        }
    }
}