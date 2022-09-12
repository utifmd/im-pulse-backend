package com.dudegenuine.app.controller

import com.dudegenuine.app.model.WebResponse
import com.dudegenuine.app.model.token.TokenRequest
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.ITokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.addToken(
    service: ITokenService){
    post("api/tokens"){
        val request: TokenRequest = call.receive()
        val response = service.addToken(request)

        call.respond(
            status = HttpStatusCode.Created,
            message = WebResponse(response)
        )
    }
}
fun Route.removeToken(
    service: ITokenService){
    delete("api/tokens/{token}"){
        val content = call.parameters["token"] ?: throw BadRequestException("token")
        val response = service.removeToken(content)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(response)
        )
    }
}