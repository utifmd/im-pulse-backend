package com.dudegenuine.di

import com.dudegenuine.app.mapper.IUserMapper
import com.dudegenuine.app.mapper.UserMapper
import com.dudegenuine.app.repository.IUserRepository
import com.dudegenuine.app.repository.UserRepository
import com.dudegenuine.app.service.IUserService
import com.dudegenuine.app.service.UserService
import org.koin.dsl.module
import org.ktorm.database.Database
/**
 * Wed, 31 Aug 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/

val appModule = module {
    single {
        val url = getProperty("BASE_URL") as String
        val user = getProperty("USERNAME") as String
        val password = getProperty("PASSWORD") as String

        Database.connect(url, user = user, password = password)
    }
}