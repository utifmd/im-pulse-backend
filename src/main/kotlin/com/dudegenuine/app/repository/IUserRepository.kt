package com.dudegenuine.app.repository

import com.dudegenuine.app.model.user.*

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IUserRepository {
    fun getLeftUsersCensor(pageAndSize: Pair<Int, Int>): List<UserCensorResponse>
    //fun getInnerUsersCensor(pageAndSize: Pair<Int, Int>): List<UserCensorResponse>
    fun getUserCensor(userId: String): UserCensorResponse?
    fun getUserComplete(userId: String): UserCompleteResponse?
    fun postUser(userRequest: UserCreateRequest)
    fun putUser(userRequest: UserUpdateRequest)
    fun deleteUser(userId: String)

//fun getAllUsersCensor(): List<UserCensorResponse>
    //fun postUser(userRequest: UserCreateRequest): UserCompleteResponse?
    //fun putUser(userRequest: UserUpdateRequest): UserCompleteResponse?
}