package com.dudegenuine.app.service

import com.dudegenuine.app.model.user.UserCreateRequest
import com.dudegenuine.app.repository.contract.IUserRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.InternalErrorException
import com.dudegenuine.app.repository.validation.NotFoundException
import com.dudegenuine.app.service.contract.IUserService

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class UserService(
    private val repository: IUserRepository): IUserService {
    override fun pagedUsers(pageAndSize: Pair<Long, Int>) =
        try{ repository.getUsersHalf(pageAndSize) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    override fun addUser(createRequest: UserCreateRequest) =
        try { createRequest.let(repository::createUser) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    override fun findUser(userId: String) =
        try { repository.getUserHalfOrNull(userId) ?: throw NotFoundException() } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    override fun removeUser(userId: String) =
        try { userId.let(repository::deleteUser) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
}