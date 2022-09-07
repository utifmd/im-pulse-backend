package com.dudegenuine.di

import com.dudegenuine.app.service.FileService
import com.dudegenuine.app.service.IFileService
import com.dudegenuine.app.service.IUserService
import com.dudegenuine.app.service.UserService
import org.koin.dsl.module

/**
 * Thu, 08 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
val serviceModule = module {
    single<IUserService> {
        UserService(get())
    }
    single<IFileService> {
        FileService(get())
    }
}