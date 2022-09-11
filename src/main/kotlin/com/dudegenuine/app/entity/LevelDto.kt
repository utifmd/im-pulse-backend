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
object Levels: UUIDTable("levels"){
    val status = varchar("status", 38)
    val createdAt = long("created_at")
    val userId = reference("user_id", Users, ReferenceOption.CASCADE).nullable()
}
class LevelDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var status by Levels.status
    var createdAt by Levels.createdAt
    var userDto by UserDto optionalReferencedOn Levels.userId
    companion object: EntityClass<UUID, LevelDto>(Levels)
}