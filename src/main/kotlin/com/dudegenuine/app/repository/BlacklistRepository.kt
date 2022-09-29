package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.BlacklistDto
import com.dudegenuine.app.entity.Blacklists
import com.dudegenuine.app.entity.UserDto
import com.dudegenuine.app.entity.Users
import com.dudegenuine.app.mapper.contract.IBlacklistMapper
import com.dudegenuine.app.model.blacklist.BlacklistRequest
import com.dudegenuine.app.repository.common.Utils
import com.dudegenuine.app.repository.contract.IBlacklistRepository
import com.dudegenuine.app.repository.validation.AlreadyExistException
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Sat, 24 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class BlacklistRepository(
    private val mapper: IBlacklistMapper): IBlacklistRepository {
    init {
        transaction { SchemaUtils.create(Blacklists) }
    }
    override fun createBlacklist(request: BlacklistRequest) = transaction {
        val mUserId = request.userId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val targetUserId = request.userId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val blacklists = BlacklistDto.find{
            Blacklists.userId.eq(mUserId) and
                Blacklists.targetUserId.eq(targetUserId) }

        if (!blacklists.empty()) throw AlreadyExistException()

        val dto = BlacklistDto.new {
            targetUserDto = UserDto[targetUserId]
            createdAt = System.currentTimeMillis()
            userId = EntityID(mUserId, Users)
        }
        dto.let(mapper::asResponse)
    }
    override fun deleteBlacklist(blacklistId: String): String = transaction {
        val id = blacklistId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val dto = BlacklistDto.findById(id) ?: throw NotFoundException()
        dto.delete()
        dto.id.value.toString()
    }
    override fun listBlacklist(userId: String, pageAndSize: Pair<Long, Int>) = transaction {
        val id = userId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        BlacklistDto.find{ Blacklists.userId eq id }
            .limit(pageAndSize.second, pageAndSize.first)
            .map(mapper::asResponse)
    }
    override fun isAccessBlocked(senderAndRecipient: Pair<String, String>) = transaction {
        val userId = senderAndRecipient.second.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val targetUserId = senderAndRecipient.first.let(Utils::uuidOrNull) ?: throw BadRequestException()

        !BlacklistDto.find{ Blacklists.userId.eq(userId) and Blacklists.targetUserId.eq(targetUserId) }.empty()
    }
}