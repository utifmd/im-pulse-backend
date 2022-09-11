package com.dudegenuine.app.controller

import com.dudegenuine.app.model.WebResponse
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
        val fileMultipartData = call.receiveMultipart()
        val fileId = service.uploadFile(fileMultipartData)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(fileId)
        )
    }
}
fun Route.findFile(
    service: IFileService
){
    get("api/files/{fileId}") {
        val fileId = call.parameters["fileId"] ?: throw BadRequestException()
        val file = service.readFile(fileId)

        val fileType = when(file.type){
            ContentType.Image.PNG.contentType -> ContentType.Image.PNG
            ContentType.Image.GIF.contentType -> ContentType.Image.GIF
            else -> ContentType.Image.JPEG
        }
        call.respondBytes(
            status = HttpStatusCode.OK,
            contentType = fileType,
            bytes = file.data,
        )
    }
}
fun Route.removeFile(
    service: IFileService
){
    delete("api/files/{fileId}") {
        val fileId = call.parameters["fileId"] ?: throw BadRequestException()
        service.deleteFile(fileId)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(fileId)
        )
    }
}