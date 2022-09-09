package com.dudegenuine.app.controller

import com.dudegenuine.app.model.WebResponse
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.IAuthService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.listAuths(
    service: IAuthService){
    get("api/auths") {
        println("list auths trigger")
        val params = call.request.queryParameters
        val page = params["page"]
            ?. let(Integer::parseInt)
            ?. toLong()
            ?: throw BadRequestException("page")
        val size = params["size"]
            ?. let(Integer::parseInt)
            ?: throw BadRequestException("size")
        val list = service.readAuth(page to size)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(list)
        )
    }
}