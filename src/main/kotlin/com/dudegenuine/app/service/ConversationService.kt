package com.dudegenuine.app.service

import com.dudegenuine.app.entity.MessageDto.Companion.TYPE_MESSAGE
import com.dudegenuine.app.entity.MessageDto.Companion.TYPE_TYPING
import com.dudegenuine.app.model.conversation.session.ConversationSession
import com.dudegenuine.app.model.message.MessageCreateRequest
import com.dudegenuine.app.repository.contract.IConversationRepository
import com.dudegenuine.app.repository.contract.IMessageRepository
import com.dudegenuine.app.repository.validation.BadRequestException
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
    private val messageRepository: IMessageRepository): IConversationService {

    override suspend fun onJoinConversation(
        session: ConversationSession) = with(session) {
        try {
            val converseId = run(converseRepository::onSessionConnect)
            socket.incoming.consumeEach { frame -> // observe session event
                if (frame is Frame.Text)
                    onBroadcastConverse(converseId, session, frame.readText())
            }
        } catch (e: Exception){
            e.printStackTrace()
            socket.close(CloseReason(CloseReason.Codes.PROTOCOL_ERROR, e.localizedMessage))
        } finally {
            converseRepository.onSessionDisconnect(this)
        }
    }
    override fun listConversations(userId: String, pageAndSize: Pair<Long, Int>) =
        try { converseRepository.readConversations(userId, pageAndSize) } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
    override fun removeConversation(conversationId: String) =
        try { converseRepository.deleteConversation(conversationId) } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }

    private suspend fun onBroadcastConverse(
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