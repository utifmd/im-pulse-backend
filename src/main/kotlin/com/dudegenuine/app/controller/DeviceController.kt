package com.dudegenuine.app.controller

import com.dudegenuine.app.model.WebResponse
import com.dudegenuine.app.model.device.DeviceRequest
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.IDeviceService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.addDevice(
    service: IDeviceService){
    post("api/devices"){
        val request: DeviceRequest = try { call.receive() } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
        val response = service.addDevice(request)

        call.respond(
            status = HttpStatusCode.Created,
            message = WebResponse(response)
        )
    }
}
fun Route.removeDevice(
    service: IDeviceService){
    delete("api/devices/{token}"){
        val content = call.parameters["token"] ?: throw BadRequestException("token")
        val response = service.removeDevice(content)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(response)
        )
    }
}