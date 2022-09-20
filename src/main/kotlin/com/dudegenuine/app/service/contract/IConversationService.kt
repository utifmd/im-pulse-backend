package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.conversation.Conversation
import com.dudegenuine.app.model.conversation.ConversationResponse
import com.dudegenuine.app.model.conversation.session.ConverseSession
import io.ktor.websocket.*

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IConversationService {
    suspend fun onJoinConversation(socket: WebSocketSession, session: ConverseSession)
    fun listConversations(userId: String, pageAndSize: Pair<Long, Int>): List<ConversationResponse>
    fun removeConversation(conversationId: String): String
}