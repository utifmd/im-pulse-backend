package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.user.*

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IUserRepository {
    fun createUser(request: UserCreateRequest): UserResponse
    fun deleteUser(userId: String): String
    fun getUserHalfOrNull(userId: String): UserResponse?
    fun getUsersHalf(pageAndSize: Pair<Long, Int>): List<UserResponse>
}