package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.UUID

/**
 * Fri, 23 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Blacklists: UUIDTable("blacklists"){
    val createdAt = long("created_at")
    val targetUserId = reference("target_user_id", Users).nullable()
    val userId = reference("user_id", Users, ReferenceOption.CASCADE)
}
class BlacklistDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var createdAt by Blacklists.createdAt
    var targetUserDto by UserDto optionalReferencedOn Blacklists.targetUserId
    var userId by Blacklists.userId

    companion object: EntityClass<UUID, BlacklistDto>(Blacklists)
}