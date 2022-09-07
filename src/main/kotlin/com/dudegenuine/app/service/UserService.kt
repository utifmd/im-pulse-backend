package com.dudegenuine.app.service

import com.dudegenuine.app.model.user.UserCreateRequest
import com.dudegenuine.app.repository.IUserRepository
/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class UserService(
    private val repository: IUserRepository): IUserService {
    override fun listUsers() = repository.getAllUsersCensor()
    override fun listUsers(
        pageAndSize: Pair<Int, Int>
    ) = repository.getUsersCensor(pageAndSize)
    override fun createUser(
        createRequest: UserCreateRequest
    ) = repository.postUser(createRequest)
}