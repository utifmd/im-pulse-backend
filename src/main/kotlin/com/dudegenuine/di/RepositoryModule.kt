package com.dudegenuine.di

import com.dudegenuine.app.mapper.*
import com.dudegenuine.app.mapper.contract.*
import com.dudegenuine.app.repository.*
import com.dudegenuine.app.repository.contract.*
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
    single<IProfileRepository> {
        ProfileRepository(get(), get())
    }
    single<IFileRepository> {
        FileRepository(get(), get())
    }
    single<ILevelRepository> {
        LevelRepository(get(), get())
    }
    single<IImageRepository> {
        ImageRepository(get(), get())
    }
}

val mapperModule = module {
    single<IAuthMapper> { AuthMapper() }
    single<IUserMapper> { UserMapper() }
    single<IProfileMapper> { ProfileMapper() }
    single<IFileMapper> { FileMapper() }
    single<ILevelMapper> { LevelMapper() }
    single<IImageMapper> { ImageMapper() }
}