package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.UUID


/**
 * Mon, 05 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Roles: UUIDTable("roles"){
    val current = varchar("current", 38)
    val latest = varchar("latest", 38)
    val createdAt = long("created_at")
    val updatedAt = long("updated_at")
    val userId = reference("user_id", Users, ReferenceOption.CASCADE).nullable()
}
class RoleDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var current by Roles.current
    var latest by Roles.latest
    var createdAt by Roles.createdAt
    var updatedAt by Roles.createdAt
    var userDto by UserDto optionalReferencedOn Roles.userId
    companion object: EntityClass<UUID, RoleDto>(Roles)
}