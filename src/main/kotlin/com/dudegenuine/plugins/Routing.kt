package com.dudegenuine.plugins

import com.dudegenuine.app.controller.*
import com.dudegenuine.app.service.contract.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val authService: IAuthService by inject()
    val userService: IUserService by inject()
    val profileService: IProfileService by inject()
    val fileService: IFileService by inject()
    val levelService: IRoleService by inject()
    val imageService: IImageService by inject()
    val deviceService: IDeviceService by inject()
    val contactService: IContactService by inject()

    install(Routing){
        with(authService){
            apply(::signIn)
            apply(::signUp)
            apply(::findAuth)
            apply(::patchAuth)
            apply(::listAuths)
            apply(::removeAuth)
            apply(::isUsernameExist)
            authenticate()
            claimAuthId()
        }
        with(contactService){
            apply(::addContact)
            apply(::putContact)
        }
        with(userService){
            apply(::addUser)
            apply(::findUser)
            apply(::removeUser)
            apply(::listUsers)
        }
        with(profileService){
            apply(::addProfile)
            apply(::patchProfile)
        }
        with(fileService){
            apply(::addFile)
            apply(::findFile)
            apply(::removeFile)
        }
        with(levelService){
            apply(::addRole)
            apply(::findRole)
        }
        with(deviceService){
            apply(::addDevice)
            apply(::removeDevice)
        }

        imageService.apply(::addImage)
        configureExceptionRoutes()
    }
}