package com.dudegenuine.di

import com.dudegenuine.app.service.*
import com.dudegenuine.app.service.contract.*
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
    single<IProfileService> {
        ProfileService(get())
    }
    single<IFileService> {
        FileService(get())
    }
    single<ILevelService> {
        LevelService(get())
    }
    single<IImageService> {
        ImageService(get())
    }
    single<ITokenService> {
        TokenService(get())
    }
}