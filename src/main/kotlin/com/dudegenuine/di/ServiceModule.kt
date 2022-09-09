package com.dudegenuine.di

import com.dudegenuine.app.service.*
import org.koin.dsl.module

/**
 * Thu, 08 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
val serviceModule = module {
    single<IAuthService> {
        AuthService(get())
    }
    single<IUserService> {
        UserService(get())
    }
    single<IFileService> {
        FileService(get())
    }
}