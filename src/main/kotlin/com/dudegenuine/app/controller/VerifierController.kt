package com.dudegenuine.app.controller

import com.dudegenuine.app.model.SuccessResponse
import com.dudegenuine.app.model.verifier.VerifierRequest
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.IVerifierService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Wed, 28 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.add(service: IVerifierService){
    post("api/verifiers"){
        val request: VerifierRequest = try { call.receive() } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
        val response = service.add(request)
        call.respond(
            status = HttpStatusCode.Created,
            message = SuccessResponse(response)
        )
    }
}
fun Route.remove(service: IVerifierService){
    delete("api/verifiers/{verifierId}"){
        val verifierId = call.parameters["verifierId"] ?: throw BadRequestException("verifierId")
        val response = service.remove(verifierId)
        call.respond(
            status = HttpStatusCode.OK,
            message = SuccessResponse(response)
        )
    }
}
fun Route.paged(service: IVerifierService){
    get("api/verifiers/{userId}"){
        val userId = call.parameters["userId"] ?: throw BadRequestException("userId")
        val page = call.request.queryParameters["page"]?.let(Integer::parseInt)?.toLong() ?: throw BadRequestException("page")
        val size = call.request.queryParameters["page"]?.let(Integer::parseInt) ?: throw BadRequestException("size")
        val response = service.paged(userId, page to size)
        call.respond(
            status = HttpStatusCode.OK,
            message = SuccessResponse(response)
        )
    }
}
fun Route.find(service: IVerifierService){
    get("api/verifiers/{verifierId}"){
        val verifierId = call.parameters["verifierId"] ?: throw BadRequestException("verifierId")
        val response = service.find(verifierId)
        call.respond(
            status = HttpStatusCode.OK,
            message = SuccessResponse(response)
        )
    }
}