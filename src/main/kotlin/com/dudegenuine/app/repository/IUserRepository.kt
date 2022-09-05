package com.dudegenuine.app.repository

import com.dudegenuine.app.model.User

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IUserRepository {
    fun getAllUsers(): List<User>
    fun getUser(userId: String): User?
}