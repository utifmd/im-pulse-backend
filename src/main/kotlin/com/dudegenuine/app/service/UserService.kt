package com.dudegenuine.app.service

import com.dudegenuine.app.model.User
import com.dudegenuine.app.repository.IUserRepository
/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class UserService(
    private val repository: IUserRepository
): IUserService {
    override fun getAllUsers(): List<User> = repository.getAllUsers()
}