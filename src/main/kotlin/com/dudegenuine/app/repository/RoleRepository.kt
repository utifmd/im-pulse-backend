package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.RoleDto
import com.dudegenuine.app.entity.Roles
import com.dudegenuine.app.entity.UserDto
import com.dudegenuine.app.mapper.contract.IRoleMapper
import com.dudegenuine.app.model.role.RoleCreateRequest
import com.dudegenuine.app.repository.contract.IRoleRepository
import com.dudegenuine.app.repository.validation.AlreadyExistException
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class RoleRepository(
    private val mapper: IRoleMapper, database: Database): IRoleRepository {
    init {
        transaction { SchemaUtils.create(Roles) }
    }
    override fun createRole(request: RoleCreateRequest) = transaction {
        val (mStatus, mUserId) = request

        val levels = RoleDto.find{ Roles.userId eq UUID.fromString(mUserId) }
        if(!levels.empty()) throw AlreadyExistException()

        val dto = RoleDto.new {
            current = mStatus
            latest = mStatus
            createdAt = System.currentTimeMillis()
            userDto = UserDto[UUID.fromString(mUserId)]
        }
        dto.let(mapper::asResponse)
    }

    override fun readRole(roleId: String) = transaction {
        val dto = RoleDto.findById(UUID.fromString(roleId)) ?: throw NotFoundException()

        dto.let(mapper::asResponse)
    }
}