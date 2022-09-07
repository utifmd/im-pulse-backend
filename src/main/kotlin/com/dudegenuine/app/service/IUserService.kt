package com.dudegenuine.app.service

import com.dudegenuine.app.model.user.UserCensorResponse
import com.dudegenuine.app.model.user.UserCompleteResponse
import com.dudegenuine.app.model.user.UserCreateRequest
import com.dudegenuine.app.model.user.UserUpdateRequest

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IUserService {
    fun listUsers(): List<UserCensorResponse>
    fun listUsers(pageAndSize: Pair<Int, Int>): List<UserCensorResponse>
    fun createUser(createRequest: UserCreateRequest)

    /*fun updateUser(updateRequest: UserUpdateRequest)
    fun findUser(createRequest: UserCreateRequest): UserCompleteResponse*/
    /*fun signInUser(authRequest: AuthLoginRequest): List<UserCompleteResponse>
    fun signUpUser(authRequest: AuthCreateRequest): List<UserCompleteResponse>*/
}