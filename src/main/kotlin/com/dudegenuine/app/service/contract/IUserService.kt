package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.user.UserCreateRequest
import com.dudegenuine.app.model.user.UserResponse

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IUserService {
    fun addUser(createRequest: UserCreateRequest): UserResponse
    fun findUser(userId: String): UserResponse
    fun pagedUsers(pageAndSize: Pair<Long, Int>): List<UserResponse>
    fun removeUser(userId: String): String
}
