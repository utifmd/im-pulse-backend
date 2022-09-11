package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.user.UserCensorResponse
import com.dudegenuine.app.model.user.UserCreateRequest

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IUserService {
    fun addUser(createRequest: UserCreateRequest): UserCensorResponse
    fun findUser(userId: String): UserCensorResponse
    fun listUsers(pageAndSize: Pair<Long, Int>): List<UserCensorResponse>
    fun removeUser(userId: String): String
}
