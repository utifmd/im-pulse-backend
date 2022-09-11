package com.dudegenuine.di

import com.dudegenuine.app.mapper.*
import com.dudegenuine.app.mapper.contract.IAuthMapper
import com.dudegenuine.app.mapper.contract.IFileMapper
import com.dudegenuine.app.mapper.contract.ILevelMapper
import com.dudegenuine.app.mapper.contract.IUserMapper
import com.dudegenuine.app.repository.*
import com.dudegenuine.app.repository.contract.IAuthRepository
import com.dudegenuine.app.repository.contract.IFileRepository
import com.dudegenuine.app.repository.contract.ILevelRepository
import com.dudegenuine.app.repository.contract.IUserRepository
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
    single<ILevelRepository> {
        LevelRepository(get(), get())
    }
}

val mapperModule = module {
    single<IAuthMapper> { AuthMapper() }
    single<IUserMapper> { UserMapper() }
    single<IFileMapper> { FileMapper() }
    single<ILevelMapper> { LevelMapper() }
}