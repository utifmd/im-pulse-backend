package com.dudegenuine.app.service

import com.dudegenuine.app.model.User

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IUserService {
    fun getAllUsers(): List<User>
}