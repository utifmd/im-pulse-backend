package com.dudegenuine.plugins

import com.dudegenuine.app.controller.*
import com.dudegenuine.app.service.IFileService
import com.dudegenuine.app.service.IUserService
import io.ktor.server.routing.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userService: IUserService by inject()
    val fileService: IFileService by inject()

    install(Routing){
        with(userService){
            apply(::createUser)
            apply(::readUser)
            apply(::deleteUser)
            apply(::listUsers)
        }
        with(fileService){
            apply(::uploadFile)
            apply(::readFile)
            apply(::deleteFile)
        }
        configureExceptionRoutes()
    }
}