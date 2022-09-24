package com.dudegenuine.app.controller

import com.dudegenuine.app.model.SuccessResponse
import com.dudegenuine.app.model.message.MessageCreateRequest
import com.dudegenuine.app.model.message.MessageUpdateRequest
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.IMessageService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Tue, 20 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.addMessage(service: IMessageService){
    post("api/messages"){
        val request: MessageCreateRequest = try { call.receive() } catch (e: Exception){
            throw BadRequestException()
        }
        val response = service.addMessage(request)
        call.respond(
            status = HttpStatusCode.Created,
            message = SuccessResponse(response)
        )
    }
}
fun Route.putMessage(service: IMessageService){
    put("api/messages"){
        val request: MessageUpdateRequest = try { call.receive() } catch (e: Exception){
            throw BadRequestException()
        }
        val response = service.putMessage(request)
        call.respond(
            status = HttpStatusCode.OK,
            message = SuccessResponse(response)
        )
    }
}
fun Route.pagedMessages(service: IMessageService){
    get("api/messages/{conversationId}"){
        val params = call.request.queryParameters
        val conversationId = call.parameters["conversationId"] ?: throw BadRequestException("conversationId")
        val page = params["page"]?.let(Integer::parseInt)?.toLong() ?: throw BadRequestException("page")
        val size = params["size"]?.let(Integer::parseInt) ?: throw BadRequestException("page")
        val response = service.pagedMessages(conversationId, page to size)
        call.respond(
            status = HttpStatusCode.OK,
            message = SuccessResponse(response)
        )
    }
}