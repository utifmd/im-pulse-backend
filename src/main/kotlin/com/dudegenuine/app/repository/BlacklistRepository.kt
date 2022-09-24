package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.BlacklistDto
import com.dudegenuine.app.entity.Blacklists
import com.dudegenuine.app.entity.UserDto
import com.dudegenuine.app.entity.Users
import com.dudegenuine.app.mapper.contract.IBlacklistMapper
import com.dudegenuine.app.model.blacklist.BlacklistRequest
import com.dudegenuine.app.model.blacklist.BlacklistResponse
import com.dudegenuine.app.repository.contract.IBlacklistRepository
import com.dudegenuine.app.repository.validation.AlreadyExistException
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

/**
 * Sat, 24 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class BlacklistRepository(
    private val mapper: IBlacklistMapper, database: Database): IBlacklistRepository {
    init {
        transaction { SchemaUtils.create(Blacklists) }
    }
    override fun createBlacklist(request: BlacklistRequest) = transaction {
        val blacklists = BlacklistDto.find{
            Blacklists.userId.eq(UUID.fromString(request.userId)) and
                Blacklists.targetUserId.eq(UUID.fromString(request.targetUserId)) }

        if (!blacklists.empty()) throw AlreadyExistException()

        val dto = BlacklistDto.new {
            targetUserDto = UserDto[UUID.fromString(request.targetUserId)]
            createdAt = System.currentTimeMillis()
            userId = EntityID(UUID.fromString(request.userId), Users)
        }
        dto.let(mapper::asResponse)
    }
    override fun deleteBlacklist(blacklistId: String): String = transaction {
        val dto = BlacklistDto.findById(UUID.fromString(blacklistId)) ?: throw NotFoundException()
        dto.delete()
        dto.id.value.toString()
    }
    override fun listBlacklist(userId: String, pageAndSize: Pair<Long, Int>) = transaction {
        BlacklistDto.find{ Blacklists.userId eq UUID.fromString(userId) }
            .limit(pageAndSize.second, pageAndSize.first)
            .map(mapper::asResponse)
    }
    override fun isAccessBlocked(senderAndRecipient: Pair<String, String>) = transaction {
        val userId = UUID.fromString(senderAndRecipient.second)
        val targetUserId = UUID.fromString(senderAndRecipient.first)

        !BlacklistDto.find{ Blacklists.userId.eq(userId) and Blacklists.targetUserId.eq(targetUserId) }.empty()
    }
}