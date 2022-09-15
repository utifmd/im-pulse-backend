package com.dudegenuine.app.service

import com.dudegenuine.app.entity.MessageDto.Companion.TYPE_MESSAGE
import com.dudegenuine.app.model.conversation.Conversation
import com.dudegenuine.app.model.conversation.session.ConverseSession
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
        socket: WebSocketSession, session: ConverseSession) = with(converseRepository) {

        val (mConverseId) = session
        val conversation = Conversation(mConverseId, socket)

        try {
            onSessionConnect(conversation)
            socket.incoming.consumeEach { frame ->
                if (frame is Frame.Text)
                    onBroadcastConverse(frame.readText())
            }
        } catch (e: Exception){
            e.printStackTrace()
            socket.close(CloseReason(CloseReason.Codes.PROTOCOL_ERROR, e.localizedMessage))
        } finally {
            onSessionDisconnect(conversation)
        }
    }
    private suspend fun onBroadcastConverse(payload: String) {
        val message: MessageCreateRequest = try { Json.decodeFromString(payload) } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
        if (message.type == TYPE_MESSAGE) // ~> then if userId isEquals it should be opposite broadcast target
            message.let(messageRepository::createMessage)

        converseRepository.onSendBroadcast(
            converseId = message.converseId,
            encodedMessage = payload
        )
    }
}