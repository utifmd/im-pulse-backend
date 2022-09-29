package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.RoleDto
import com.dudegenuine.app.entity.Roles
import com.dudegenuine.app.entity.Users
import com.dudegenuine.app.mapper.contract.IRoleMapper
import com.dudegenuine.app.model.role.RoleCreateRequest
import com.dudegenuine.app.repository.common.Utils
import com.dudegenuine.app.repository.contract.IRoleRepository
import com.dudegenuine.app.repository.validation.AlreadyExistException
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class RoleRepository(
    private val mapper: IRoleMapper): IRoleRepository {
    init {
        transaction { SchemaUtils.create(Roles) }
    }
    override fun createRole(request: RoleCreateRequest) = transaction {
        val (mStatus, mUserId) = request
        val myUserId = mUserId.let(Utils::uuidOrNull) ?: throw BadRequestException()

        val levels = RoleDto.find{ Roles.userId eq myUserId }
        if(!levels.empty()) throw AlreadyExistException()

        val dto = RoleDto.new {
            current = mStatus
            latest = mStatus
            createdAt = System.currentTimeMillis()
            userId = EntityID(myUserId, Users) //UserDto[UUID.fromString(mUserId)]
        }
        dto.let(mapper::asResponse)
    }

    override fun readRole(roleId: String) = transaction {
        val mRoleId = roleId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val dto = RoleDto.findById(mRoleId) ?: throw NotFoundException()

        dto.let(mapper::asResponse)
    }
}