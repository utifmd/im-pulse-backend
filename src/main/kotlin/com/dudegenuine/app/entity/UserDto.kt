package com.dudegenuine.app.entity

import com.dudegenuine.app.entity.LevelDto.Companion.optionalBackReferencedOn
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.UUID

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Users: UUIDTable("users"){
    val firstName = varchar("first_name", 127)
    val lastName = varchar("last_name", 127)
    val createdAt = long("created_at")
    val updatedAt = long("updated_at").nullable()

    val authId = reference("auth_id", Auths, ReferenceOption.CASCADE) // as a parent
}
class UserDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var firstName by Users.firstName
    var lastName by Users.lastName
    var createdAt by Users.createdAt
    var updatedAt by Users.updatedAt

    var authId by AuthDto referencedOn Users.authId
    val level by LevelDto optionalReferrersOn Levels.userId //val level by LevelDto optionalBackReferencedOn Levels.userId
    companion object: EntityClass<UUID, UserDto>(Users)
}