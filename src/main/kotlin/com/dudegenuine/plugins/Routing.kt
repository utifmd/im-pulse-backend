package com.dudegenuine.plugins

import com.dudegenuine.app.controller.listAllUsers
import com.dudegenuine.app.controller.listUsers
import com.dudegenuine.app.controller.createUser
import com.dudegenuine.app.service.IUserService
import io.ktor.server.routing.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userService: IUserService by inject()

    install(Routing){
        with(userService){
            apply(::createUser)
            apply(::listAllUsers)
            apply(::listUsers)
        }
    }
}