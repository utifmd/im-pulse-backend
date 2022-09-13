package com.dudegenuine.app.controller

import com.dudegenuine.app.model.WebResponse
import com.dudegenuine.app.model.profile.ProfileCreateRequest
import com.dudegenuine.app.model.profile.ProfileUpdateRequest
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.IProfileService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.addProfile(
    service: IProfileService){
    post("api/profiles") {
        val request: ProfileCreateRequest = try { call.receive() } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
        val response = service.addProfile(request)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(response)
        )
    }
}
fun Route.patchProfile(service: IProfileService){
    put("api/profiles") {
        val request: ProfileUpdateRequest = try { call.receive() } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
        val response = service.patchProfile(request)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(response)
        )
    }
}