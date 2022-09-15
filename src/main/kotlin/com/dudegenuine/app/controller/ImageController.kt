package com.dudegenuine.app.controller

import com.dudegenuine.app.model.SuccessResponse
import com.dudegenuine.app.model.image.ImageCreateRequest
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.IImageService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.addImage(
    service: IImageService){
    post("api/images") {
        val request: ImageCreateRequest = try { call.receive() } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
        val response = service.addImage(request)

        call.respond(
            status = HttpStatusCode.OK,
            message = SuccessResponse(response)
        )
    }
}