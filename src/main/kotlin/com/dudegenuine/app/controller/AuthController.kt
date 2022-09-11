package com.dudegenuine.app.controller

import com.dudegenuine.app.model.WebResponse
import com.dudegenuine.app.model.auth.AuthCreateRequest
import com.dudegenuine.app.model.auth.AuthUpdateRequest
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.IAuthService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.findAuth(
    service: IAuthService
){
    get("api/auths/{authId}") {
        val authId = call.parameters["authId"] ?: throw BadRequestException()
        val auth = service.findAuth(authId)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(auth)
        )
    }
}
fun Route.addAuth(
    service: IAuthService
){
    post("api/auths") {
        val request: AuthCreateRequest = call.receive()
        val auth = service.createAuth(request)

        call.respond(
            status = HttpStatusCode.Created,
            message = WebResponse(auth)
        )
    }
}
fun Route.patchAuth(
    service: IAuthService){
    put("api/auths") {
        val request: AuthUpdateRequest = call.receive()
        val auth = service.updateAuth(request)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(auth)
        )
    }
}
fun Route.isUsernameExist(
    service: IAuthService
){
    get("api/auths/is-username-exist/{username}") {
        val username = call.parameters["username"] ?: throw BadRequestException()
        val data = service.isUsernameExist(username)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(data)
        )
    }
}
fun Route.listAuths(
    service: IAuthService
){
    get("api/auths") {
        val params = call.request.queryParameters
        val page = params["page"]
            ?. let(Integer::parseInt)
            ?. minus(1)
            ?. toLong()
            ?: throw BadRequestException("page")
        val size = params["size"]
            ?. let(Integer::parseInt)
            ?: throw BadRequestException("size")
        val list = service.listAuths(page to size)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(list)
        )
    }
}
fun Route.removeAuth(
    service: IAuthService
){
    delete("api/auths/{authId}") {
        val authId = call.parameters["authId"] ?: throw BadRequestException("authId")
        val result = service.deleteAuth(authId)
        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(result)
        )
    }
}