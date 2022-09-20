package com.dudegenuine.app.controller

import com.dudegenuine.app.model.SuccessResponse
import com.dudegenuine.app.model.role.RoleCreateRequest
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.IRoleService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.addRole(
    service: IRoleService){
    post("api/roles") {
        val request: RoleCreateRequest = try { call.receive() } catch (e: Exception){
            throw BadRequestException()
        }
        val resp = service.addRole(request)

        call.respond(
            status = HttpStatusCode.Created,
            message = SuccessResponse(resp)
        )
    }
}
fun Route.findRole(
    service: IRoleService){
    get("api/roles/{roleId}"){
        val levelId = call.parameters["roleId"] ?: throw BadRequestException()
        val resp = service.findRole(levelId)

        call.respond(
            status = HttpStatusCode.OK,
            message = SuccessResponse(resp)
        )
    }
}