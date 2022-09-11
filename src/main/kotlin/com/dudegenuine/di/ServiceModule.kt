package com.dudegenuine.di

import com.dudegenuine.app.service.*
import com.dudegenuine.app.service.contract.IAuthService
import com.dudegenuine.app.service.contract.IFileService
import com.dudegenuine.app.service.contract.ILevelService
import com.dudegenuine.app.service.contract.IUserService
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
    single<ILevelService> {
        LevelService(get())
    }
}