package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.AuthDto
import com.dudegenuine.app.entity.Auths
import com.dudegenuine.app.mapper.contract.IAuthMapper
import com.dudegenuine.app.model.auth.AuthCreateRequest
import com.dudegenuine.app.model.auth.AuthUpdateRequest
import com.dudegenuine.app.repository.contract.IAuthRepository
import com.dudegenuine.app.repository.validation.AlreadyExistException
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

/**
 * Tue, 06 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class AuthRepository(
    private val mapper: IAuthMapper, database: Database): IAuthRepository {
    init {
        transaction { SchemaUtils.create(Auths) }
    }
    override fun readAuths(pageAndSize: Pair<Long, Int>) = transaction {
        val (page, size) = pageAndSize

        AuthDto.all()
            .orderBy(Auths.id to SortOrder.ASC)
            .limit(size, offset = page)
            .map(mapper::asResponse)
    }
    override fun readAuth(authId: String) = transaction {
        val dto = AuthDto.findById(UUID.fromString(authId)) ?: throw NotFoundException()

        dto.let(mapper::asResponse)
    }
    override fun deleteAuth(authId: String) = transaction {
        val dto = AuthDto.findById(UUID.fromString(authId)) ?: throw NotFoundException()

        dto.run {
            delete()
            id.value.toString()
        }
    }
    override fun isUsernameExist(text: String) = transaction {
        val auths = AuthDto.find{ Auths.username eq text }

        !auths.empty()
    }
    override fun createAuth(request: AuthCreateRequest) = transaction {
        val (mEmail, mUsername, mPassword, mLastPassword) = request

        val auths = AuthDto.find{
            (Auths.email eq mEmail) or (Auths.username eq mUsername)
        }
        if (!auths.empty()) throw AlreadyExistException()

        val dto = AuthDto.new {
            email = mEmail
            username = mUsername
            password = mPassword
            lastPassword = mLastPassword
            updatedAt = null
        }
        dto.let(mapper::asResponse)
    }
    override fun updateAuth(request: AuthUpdateRequest) = transaction {
        val (currentAuthId, mEmail, mUsername, mPassword, mLastPassword) = request
        val dto = AuthDto.findById(UUID.fromString(currentAuthId)) ?: throw NotFoundException()

        dto.apply {
            email = mEmail
            username = mUsername
            password = mPassword
            lastPassword = mLastPassword
            updatedAt = System.currentTimeMillis()
        }
        dto.let(mapper::asResponse)
    }
}