package com.dudegenuine.app.controller

import com.dudegenuine.app.model.user.UserCreateRequest
import com.dudegenuine.app.service.IUserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.createUser(
    service: IUserService){
    post("/api/users"){
        val user: UserCreateRequest = call.receive()
        service.createUser(user)
        call.respond(
            HttpStatusCode.OK
        )
    }
}
fun Route.listAllUsers(
    service: IUserService){
    get("/api/users") {
        val users = service.listUsers()
        call.respond(
            HttpStatusCode.OK, users
        )
    }
}
fun Route.listUsers(
    service: IUserService){
    get("/api/users/pagination") {
        val page = call.request.queryParameters["page"]
        val size = call.request.queryParameters["size"]
        val users = service.listUsers(
            Integer.parseInt(page) to Integer.parseInt(size)
        )
        call.respond(
            HttpStatusCode.OK, users
        )
    }
}