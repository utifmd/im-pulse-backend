package com.dudegenuine.app.controller

import com.dudegenuine.app.model.conversation.session.ConverseSession
import com.dudegenuine.app.service.contract.IConversationService
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.chatConversation(
    service: IConversationService){
    webSocket("api/chat-conversation") {
        val session = call.sessions.get<ConverseSession>()
        if (session == null){
            close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, "No session."))
            return@webSocket
        }
        service.onJoinConversation(this, session)
    }
}