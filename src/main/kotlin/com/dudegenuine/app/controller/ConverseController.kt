package com.dudegenuine.app.controller

import com.dudegenuine.app.model.SuccessResponse
import com.dudegenuine.app.model.conversation.ConversationRequest
import com.dudegenuine.app.model.conversation.session.ConversationSession
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.IConversationService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
const val PATH_CHAT_CONVERSE: String = "api/chat-conversation"
fun Route.chatConversation(
    service: IConversationService){
    webSocket(PATH_CHAT_CONVERSE) {
        val request = call.sessions.get<ConversationRequest>()
        if (request == null){
            close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, "No session."))
            return@webSocket
        }
        val (mConverseId, mFrom, mTo) = request

        val session = ConversationSession(mConverseId, mFrom, mTo, this)
        service.onJoinConversation(session)
    }
}
fun Route.listConversations(
    service: IConversationService){
    get("api/conversations/{userId}"){
        val params = call.request.queryParameters
        val userId = call.parameters["userId"] ?: throw BadRequestException("userId")
        val page = params["page"]?.let(Integer::parseInt)?.toLong() ?: throw BadRequestException("page")
        val size = params["size"]?.let(Integer::parseInt) ?: throw BadRequestException("page")
        val response = service.listConversations(userId, page to size)

        call.respond(
            status = HttpStatusCode.OK,
            message = SuccessResponse(response)
        )
    }
}
fun Route.removeConversation(
    service: IConversationService){
    delete("api/conversations/{userId}") {
        val userId = call.parameters["userId"] ?: throw BadRequestException("userId")
        service.removeConversation(userId)

        call.respond(
            status = HttpStatusCode.OK,
            message = SuccessResponse(userId)
        )
    }
}