package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.conversation.Conversation
import com.dudegenuine.app.model.conversation.session.ConverseSession
import io.ktor.websocket.*

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IConversationService {
    suspend fun onJoinConversation(socket: WebSocketSession, session: ConverseSession)
}