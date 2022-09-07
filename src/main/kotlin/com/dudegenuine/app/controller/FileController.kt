package com.dudegenuine.app.controller

import com.dudegenuine.app.service.IFileService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.readFile(
    service: IFileService){
    post("api/files") {
        val fileMultipartData = call.receiveMultipart()
        val fileId = service.uploadFile(fileMultipartData)
        call.respond(HttpStatusCode.OK, fileId)
    }
}