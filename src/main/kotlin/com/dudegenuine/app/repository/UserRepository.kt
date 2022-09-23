package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.*
import com.dudegenuine.app.mapper.contract.IUserMapper
import com.dudegenuine.app.model.user.*
import com.dudegenuine.app.repository.contract.IUserRepository
import com.dudegenuine.app.repository.validation.AlreadyExistException
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class UserRepository(
    private val mapper: IUserMapper, database: Database): IUserRepository {
    init {
        transaction { SchemaUtils.create(Users) }
    }
    override fun createUser(request: UserCreateRequest) = transaction {
        val (mFirstName, mLastName, mAuthId) = request
        val users = UserDto.find{ Users.authId eq UUID.fromString(mAuthId) }
        if(!users.empty()) throw AlreadyExistException()

        val dto = UserDto.new {
            firstName = mFirstName
            lastName = mLastName
            authDto = AuthDto[UUID.fromString(mAuthId)]
            createdAt = System.currentTimeMillis()
            updatedAt = null
        }
        dto.let(mapper::asUserHalfResponse)
    }
    override fun deleteUser(userId: String) = transaction {
        val dto = UserDto.findById(UUID.fromString(userId)) ?: throw NotFoundException()

        dto.run {
            delete()
            id.toString()
        }
    }
    override fun getUserHalfOrNull(userId: String) = transaction {
        val dto = UserDto.findById(UUID.fromString(userId)) ?: throw NotFoundException()

        dto.let(mapper::asUserHalfResponse)
    }
    override fun getUsersHalf(pageAndSize: Pair<Long, Int>) = transaction {
        val (page, size) = pageAndSize

        UserDto.all()
            .limit(size, offset = page)
            .map(mapper::asUserHalfResponse)
    }
}