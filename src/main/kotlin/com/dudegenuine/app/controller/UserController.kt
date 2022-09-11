package com.dudegenuine.app.controller

import com.dudegenuine.app.model.WebResponse
import com.dudegenuine.app.model.user.UserCreateRequest
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.IUserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.addUser(
    service: IUserService
){
    post("/api/users"){
        val user: UserCreateRequest = call.receive()
        val resp = service.addUser(user)
        call.respond(
            status = HttpStatusCode.Created,
            message = WebResponse(resp)
        )
    }
}
fun Route.findUser(
    service: IUserService
){
    get("/api/users/{userId}"){
        val userId = call.parameters["userId"] ?: throw BadRequestException("userId")
        val user = service.findUser(userId)
        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(user)
        )
    }
}
fun Route.removeUser(
    service: IUserService
){
    delete("/api/users/{userId}") {
        val userId = call.parameters["userId"] ?: throw BadRequestException("userId")
        service.removeUser(userId)
        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(userId)
        )
    }
}
fun Route.listUsers(
    service: IUserService
){
    get("/api/users") {
        val params = call.request.queryParameters
        val page = params["page"]?.let(Integer::parseInt)?.toLong() ?: throw BadRequestException("page")
        val size = params["size"]?.let(Integer::parseInt) ?: throw BadRequestException("size")
        val users = service.listUsers(page to size)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(users)
        )
    }
}
