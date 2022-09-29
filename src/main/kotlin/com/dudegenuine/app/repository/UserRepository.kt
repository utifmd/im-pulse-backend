package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.*
import com.dudegenuine.app.mapper.contract.IUserMapper
import com.dudegenuine.app.model.user.*
import com.dudegenuine.app.repository.common.Utils
import com.dudegenuine.app.repository.contract.IUserRepository
import com.dudegenuine.app.repository.validation.AlreadyExistException
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class UserRepository(
    private val mapper: IUserMapper): IUserRepository {
    init {
        transaction { SchemaUtils.create(Users) }
    }
    override fun createUser(request: UserCreateRequest) = transaction {
        val (mFirstName, mLastName, mContactId, mAuthId) = request
        val myAuthId = mAuthId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val myContactId = mContactId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val users = UserDto.find{ Users.authId eq myAuthId }
        if(!users.empty()) throw AlreadyExistException()

        val dto = UserDto.new {
            firstName = mFirstName
            lastName = mLastName
            createdAt = System.currentTimeMillis()
            updatedAt = null
            contactDto = ContactDto[myContactId]
            authId = EntityID(myAuthId, Auths)
        }
        dto.let(mapper::asUserResponse)
    }
    override fun deleteUser(userId: String) = transaction {
        val mUserId = userId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val dto = UserDto.findById(mUserId) ?: throw NotFoundException()

        dto.run {
            delete()
            id.toString()
        }
    }
    override fun readUser(userId: String) = transaction {
        val mUserId = userId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val dto = UserDto.findById(mUserId) ?: throw NotFoundException()

        dto.let(mapper::asUserResponse)
    }
    override fun readUsers(pageAndSize: Pair<Long, Int>) = transaction {
        val (page, size) = pageAndSize

        UserDto.all()
            .limit(size, offset = page)
            .map(mapper::asUserResponse)
    }
}