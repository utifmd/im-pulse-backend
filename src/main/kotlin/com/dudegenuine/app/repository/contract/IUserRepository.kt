package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.user.*

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IUserRepository {
    fun createUser(request: UserCreateRequest): UserCensorResponse
    fun deleteUser(userId: String): String
    fun getUserCensorOrNull(userId: String): UserCensorResponse?
    fun getUsersCensor(pageAndSize: Pair<Long, Int>): List<UserCensorResponse>
}