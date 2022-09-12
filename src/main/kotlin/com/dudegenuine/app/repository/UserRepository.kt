package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.*
import com.dudegenuine.app.mapper.contract.IUserMapper
import com.dudegenuine.app.model.user.*
import com.dudegenuine.app.repository.contract.IUserRepository
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.dao.load
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
    override fun createUser(request: UserCreateRequest): UserCensorResponse = transaction {
        val (mFirstName, mLastName, mAuthId) = request

        val users = UserDto.new {
            firstName = mFirstName
            lastName = mLastName
            authDto = AuthDto[UUID.fromString(mAuthId)]
            createdAt = System.currentTimeMillis()
            updatedAt = null
        }
        users.let(mapper::asUserCensorResponse)
    }
    override fun deleteUser(userId: String) = transaction {
        val dto = UserDto.findById(UUID.fromString(userId)) ?: throw NotFoundException()

        dto.run {
            delete()
            id.toString()
        }
    }
    override fun getUserCensorOrNull(userId: String) = transaction {
        val dto = UserDto.findById(UUID.fromString(userId)) ?: throw NotFoundException()

        dto.let(mapper::asUserCensorResponse)
    }
    override fun getUsersCensor(pageAndSize: Pair<Long, Int>) = transaction {
        val (page, size) = pageAndSize

        UserDto.all()
            .limit(size, offset = page)
            .map(mapper::asUserCensorResponse)
    }
}