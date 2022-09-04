package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.user.Users
import com.dudegenuine.app.mapper.IUserMapper
import com.dudegenuine.app.model.User
import org.ktorm.database.Database
import org.ktorm.entity.map
import org.ktorm.entity.sequenceOf

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class UserRepository(
    private val database: Database,
    private val mapper: IUserMapper): IUserRepository {

    override fun getAllUsers() = database
        .sequenceOf(Users)
        .map(mapper::asUser)
}