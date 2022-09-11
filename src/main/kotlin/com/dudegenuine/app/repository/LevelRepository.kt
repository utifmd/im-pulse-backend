package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.LevelDto
import com.dudegenuine.app.entity.Levels
import com.dudegenuine.app.entity.UserDto
import com.dudegenuine.app.entity.Users
import com.dudegenuine.app.mapper.contract.ILevelMapper
import com.dudegenuine.app.model.level.LevelCreateRequest
import com.dudegenuine.app.repository.contract.ILevelRepository
import com.dudegenuine.app.repository.validation.AlreadyExistException
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class LevelRepository(
    private val mapper: ILevelMapper, database: Database): ILevelRepository {
    init {
        transaction { SchemaUtils.create(Levels) }
    }
    override fun createLevel(request: LevelCreateRequest) = transaction {
        val (mStatus, mUserId) = request

        val levels = LevelDto.find{ Levels.userId eq UUID.fromString(mUserId) }
        if(!levels.empty()) throw AlreadyExistException()

        val dto = LevelDto.new {
            status = mStatus
            createdAt = System.currentTimeMillis()
            userDto = UserDto[UUID.fromString(mUserId)]
        }
        dto.let(mapper::asResponse)
    }

    override fun readLevel(levelId: String) = transaction {
        val dto = LevelDto.findById(UUID.fromString(levelId)) ?: throw NotFoundException()

        dto.let(mapper::asResponse)
    }
}