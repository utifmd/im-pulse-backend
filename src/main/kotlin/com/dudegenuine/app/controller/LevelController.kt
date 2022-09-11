package com.dudegenuine.app.controller

import com.dudegenuine.app.model.WebResponse
import com.dudegenuine.app.model.level.LevelCreateRequest
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.ILevelService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.addLevel(
    service: ILevelService
){
    post("api/levels") {
        val request: LevelCreateRequest = call.receive()
        val resp = service.addLevel(request)

        call.respond(
            status = HttpStatusCode.Created,
            message = WebResponse(resp)
        )
    }
}
fun Route.findLevel(
    service: ILevelService
){
    get("api/levels/{levelId}"){
        val levelId = call.parameters["levelId"] ?: throw BadRequestException()
        val resp = service.findLevel(levelId)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(resp)
        )
    }
}