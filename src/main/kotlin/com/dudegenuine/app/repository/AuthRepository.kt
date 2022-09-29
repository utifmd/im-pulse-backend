package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.AuthDto
import com.dudegenuine.app.entity.Auths
import com.dudegenuine.app.mapper.contract.IAuthMapper
import com.dudegenuine.app.model.auth.AuthRegisterRequest
import com.dudegenuine.app.model.auth.AuthLoginRequest
import com.dudegenuine.app.model.auth.AuthUpdateRequest
import com.dudegenuine.app.model.security.AuthTokenClaim
import com.dudegenuine.app.model.security.AuthTokenConfig
import com.dudegenuine.app.model.security.SaltedHash
import com.dudegenuine.app.repository.common.Utils
import com.dudegenuine.app.repository.contract.IAuthRepository
import com.dudegenuine.app.repository.contract.IAuthRepository.Companion.PASSWORD_MISMATCH
import com.dudegenuine.app.repository.dependency.IHashDependency
import com.dudegenuine.app.repository.dependency.ITokenDependency
import com.dudegenuine.app.repository.validation.AlreadyExistException
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.NotFoundException
import com.dudegenuine.app.repository.validation.UnAuthorizationException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Tue, 06 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class AuthRepository(
    private val mapper: IAuthMapper,
    private val tokenDependency: ITokenDependency,
    private val hashDependency: IHashDependency,
    private val authTokenConfig: AuthTokenConfig): IAuthRepository {
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
        val id = authId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val dto = AuthDto.findById(id) ?: throw NotFoundException()

        dto.let(mapper::asResponse)
    }
    override fun deleteAuth(authId: String) = transaction {
        val id = authId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val dto = AuthDto.findById(id) ?: throw NotFoundException()

        dto.delete()
        dto.id.value.toString()
    }
    override fun onSignIn(request: AuthLoginRequest): String {
        val dto = AuthDto.find{ Auths.payload eq request.payload }.firstOrNull() ?: throw NotFoundException()
        val saltedHash = SaltedHash(dto.password, dto.salt)

        if (!hashDependency.verify(request.password, saltedHash))
            throw UnAuthorizationException(PASSWORD_MISMATCH)

        return tokenDependency.generate(
            authTokenConfig, AuthTokenClaim("authId", dto.id.value.toString())
        )
    }
    override fun onSignUp(request: AuthRegisterRequest) = transaction {
        val (mEmail, mPassword) = request
        val (mHashedPassword, mSalt) = hashDependency.generateSaltedHash(mPassword)
        val auths = AuthDto.find{ Auths.payload eq mEmail }

        if (!auths.empty()) throw AlreadyExistException()

        AuthDto.new {
            payload = mEmail
            password = mHashedPassword
            lastPassword = mHashedPassword
            salt = mSalt
            updatedAt = null
        }
        Unit
    }
    override fun updateAuth(request: AuthUpdateRequest) = transaction {
        val (currentAuthId, mEmailOrUsername, mPassword, mLastPassword) = request
        val id = currentAuthId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val dto = AuthDto.findById(id) ?: throw NotFoundException()

        dto.apply {
            payload = mEmailOrUsername
            password = mPassword
            lastPassword = mLastPassword
            updatedAt = System.currentTimeMillis()
        }
        dto.let(mapper::asResponse)
    }
}