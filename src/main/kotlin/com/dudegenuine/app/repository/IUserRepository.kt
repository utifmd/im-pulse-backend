package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.UserDto
import com.dudegenuine.app.model.user.*

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IUserRepository {
    fun getAllUsersCensor(): List<UserCensorResponse>
    fun getUsersCensor(pageAndSize: Pair<Int, Int>): List<UserCensorResponse>
    fun getUserCensor(userId: String): UserCensorResponse?
    fun getUserComplete(userId: String): UserCompleteResponse?
    fun postUser(userRequest: UserCreateRequest)
    //fun postUser(userRequest: UserCreateRequest): UserCompleteResponse?
    fun putUser(userRequest: UserUpdateRequest)
    //fun putUser(userRequest: UserUpdateRequest): UserCompleteResponse?
}