package com.dudegenuine.plugins

import com.dudegenuine.app.controller.listUsers
import com.dudegenuine.app.service.IUserService
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.koin.ktor.ext.inject
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

fun Application.configureRouting() {
    val userService: IUserService by inject()

    install(Routing){
        listUsers(userService)
    }
}