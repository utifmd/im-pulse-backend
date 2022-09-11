package com.dudegenuine.plugins

import com.dudegenuine.app.controller.*
import com.dudegenuine.app.service.contract.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val authService: IAuthService by inject()
    val userService: IUserService by inject()
    val fileService: IFileService by inject()
    val levelService: ILevelService by inject()
    val imageService: IImageService by inject()

    install(Routing){
        with(authService){
            apply(::addAuth)
            apply(::findAuth)
            apply(::patchAuth)
            apply(::listAuths)
            apply(::removeAuth)
            apply(::isUsernameExist)
        }
        with(userService){
            apply(::addUser)
            apply(::findUser)
            apply(::removeUser)
            apply(::listUsers)
        }
        with(fileService){
            apply(::addFile)
            apply(::findFile)
            apply(::removeFile)
        }
        with(levelService){
            apply(::addLevel)
            apply(::findLevel)
        }
        with(imageService){
            apply(::addImage)
        }
        configureExceptionRoutes()
    }
}