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
object Tokens: UUIDTable("tokens") {
    val content = varchar("content", 225)
    val type = varchar("type", 36)
    val userId = reference("user_id", Users, ReferenceOption.CASCADE)
}
class TokenDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var content by Tokens.content
    var type by Tokens.type

    var userDto by UserDto referencedOn Tokens.userId
    companion object: EntityClass<UUID, TokenDto>(Tokens)
}