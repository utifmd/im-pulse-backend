package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.TokenDto
import com.dudegenuine.app.entity.Tokens
import com.dudegenuine.app.entity.UserDto
import com.dudegenuine.app.mapper.contract.ITokenMapper
import com.dudegenuine.app.model.token.TokenRequest
import com.dudegenuine.app.repository.contract.ITokenRepository
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class TokenRepository(
    private val mapper: ITokenMapper, database: Database): ITokenRepository {
    init {
        transaction { SchemaUtils.create(Tokens) }
    }
    override fun createToken(request: TokenRequest) = transaction{
        val (mContent, mType, mUserId) = request
        val dto = TokenDto.new {
            content = mContent
            type = mType
            userDto = UserDto[UUID.fromString(mUserId)]
        }
        dto.let(mapper::asResponse)
    }
    override fun deleteToken(content: String) = transaction {
        val tokens = TokenDto.find{ Tokens.content eq content }
        val dto = tokens.firstOrNull() ?: throw NotFoundException()

        dto.delete()
        content
    }
}