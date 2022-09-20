package com.dudegenuine.app.controller

import com.dudegenuine.app.model.SuccessResponse
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.IFileService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.addFile(
    service: IFileService){

    post("api/files") {
        val fileMultipartData = try{ call.receiveMultipart() } catch (e: Exception){
            throw BadRequestException()
        }
        val fileId = service.uploadFile(fileMultipartData)

        call.respond(
            status = HttpStatusCode.OK,
            message = SuccessResponse(fileId)
        )
    }
}
fun Route.findFile(
    service: IFileService){

    get("api/files/{fileId}") {
        val fileId = call.parameters["fileId"] ?: throw BadRequestException()
        val response = service.readFile(fileId)

        val fileType = when(response.type){
            ContentType.Image.PNG.contentType -> ContentType.Image.PNG
            ContentType.Image.GIF.contentType -> ContentType.Image.GIF
            else -> ContentType.Image.JPEG
        }
        call.respondBytes(
            status = HttpStatusCode.OK,
            contentType = fileType,
            bytes = response.data,
        )
    }
}
fun Route.removeFile(
    service: IFileService){

    delete("api/files/{fileId}") {
        val fileId = call.parameters["fileId"] ?: throw BadRequestException()
        val response = service.deleteFile(fileId)

        call.respond(
            status = HttpStatusCode.OK,
            message = SuccessResponse(response)
        )
    }
}