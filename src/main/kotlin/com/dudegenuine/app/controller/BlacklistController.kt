package com.dudegenuine.app.controller

import com.dudegenuine.app.model.SuccessResponse
import com.dudegenuine.app.model.blacklist.BlacklistRequest
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.IBlacklistService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Sat, 24 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.addBlacklist(service: IBlacklistService){
    post("api/blacklists") {
        val request: BlacklistRequest = try { call.receive() } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
        val response = service.addBlacklist(request)
        call.respond(
            status = HttpStatusCode.Created,
            message = SuccessResponse(response)
        )
    }
}
fun Route.removeBlacklist(service: IBlacklistService){
    delete("api/blacklists/{blacklistId}") {
        val blacklistId = call.parameters["blacklistId"] ?: throw BadRequestException("blacklistId")
        val response = service.removeBlacklist(blacklistId)
        call.respond(
            status = HttpStatusCode.OK,
            message = SuccessResponse(response)
        )
    }
}
fun Route.pagedBlacklists(service: IBlacklistService){
    get("api/blacklists/{userId}"){
        val userId = call.parameters["userId"] ?: throw BadRequestException("userId")
        val page = call.request.queryParameters["page"]
            ?. let(Integer::parseInt)
            ?. toLong()
            ?: throw BadRequestException("page")
        val size = call.request.queryParameters["size"]
            ?. let(Integer::parseInt)
            ?: throw BadRequestException("size")
        val response = service.pagedBlacklists(userId, page to size)
        call.respond(
            status = HttpStatusCode.OK,
            message = SuccessResponse(response)
        )
    }
}