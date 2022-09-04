package com.dudegenuine.app.controller

import com.dudegenuine.app.service.IUserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.listUsers(
    service: IUserService){
    get("/api/users") {
        val users = service.getAllUsers()

        call.respond(
            HttpStatusCode.OK, users
        )
    }
}