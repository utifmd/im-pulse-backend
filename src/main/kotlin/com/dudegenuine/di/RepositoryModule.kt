package com.dudegenuine.di

import com.dudegenuine.app.mapper.*
import com.dudegenuine.app.repository.*
import org.koin.dsl.module

/**
 * Thu, 08 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/

val repositoryModule = module {
    single<IAuthRepository> {
        AuthRepository(get(), get())
    }
    single<IUserRepository> {
        UserRepository(get(), get())
    }
    single<IFileRepository> {
        FileRepository(get(), get())
    }
}

val mapperModule = module {
    single<IAuthMapper> { AuthMapper() }
    single<IUserMapper> { UserMapper() }
    single<IFileMapper> { FileMapper() }
}