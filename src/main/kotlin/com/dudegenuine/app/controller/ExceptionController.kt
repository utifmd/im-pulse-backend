package com.dudegenuine.app.controller

import com.dudegenuine.app.repository.validation.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Thu, 08 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.configureExceptionRoutes() {
    route("/exception"){
        configureThrowableRoutes()
        configureStatusRoutes()
    }
}
private fun Route.configureThrowableRoutes(){
    get("/not-found"){
        throw NotFoundException()
    }
    get("/bad-request"){
        throw BadRequestException()
    }
    get("/already-exist"){
        throw AlreadyExistException()
    }
    get("/authorization-failed"){
        throw UnAuthorizationException()
    }
    get("/internal-error-exception"){
        throw InternalErrorException()
    }
}
private fun Route.configureStatusRoutes(){
    get("/internal-error"){
        call.respond(HttpStatusCode.InternalServerError)
    }
    get("/bad-gateway"){
        call.respond(HttpStatusCode.BadGateway)
    }
}