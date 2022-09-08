package com.dudegenuine.app.service

import com.dudegenuine.app.model.user.UserCreateRequest
import com.dudegenuine.app.repository.IUserRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.NotFoundException

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class UserService(
    private val repository: IUserRepository): IUserService {
    override fun listUsers(pageAndSize: Pair<Int, Int>) = repository.getLeftUsersCensor(pageAndSize)
    override fun createUser(createRequest: UserCreateRequest) = with(createRequest) {
        if (authId.isBlank()) throw BadRequestException()
        apply(repository::postUser); Unit
    }
    override fun findUser(userId: String) =
        repository.getUserCensor(userId) ?: throw NotFoundException()

    override fun deleteUser(userId: String) = with(userId) {
        apply(::findUser)
        apply(repository::deleteUser); Unit
    }
}