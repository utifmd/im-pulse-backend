package com.dudegenuine.di

import com.dudegenuine.app.mapper.FileMapper
import com.dudegenuine.app.mapper.IFileMapper
import com.dudegenuine.app.mapper.IUserMapper
import com.dudegenuine.app.mapper.UserMapper
import com.dudegenuine.app.repository.FileRepository
import com.dudegenuine.app.repository.IFileRepository
import com.dudegenuine.app.repository.IUserRepository
import com.dudegenuine.app.repository.UserRepository
import org.koin.dsl.module

/**
 * Thu, 08 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/

val repositoryModule = module {
    single<IUserRepository> {
        UserRepository(get(), get())
    }
    single<IFileRepository> {
        FileRepository(get(), get())
    }
}

val mapperModule = module {
    single<IUserMapper> { UserMapper() }
    single<IFileMapper> { FileMapper() }
}