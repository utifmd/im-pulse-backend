package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

/**
 * Mon, 05 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Tokens: IntIdTable("tokens") {
    val tokenId = varchar("token_id", 127).uniqueIndex()
    val content = varchar("content", 225)
    val type = varchar("type", 36)
    val ownerId = varchar("owner_id", 127)
}
class TokenDto(id: EntityID<Int>): Entity<Int>(id) {
    val tokenId by Tokens.tokenId
    val content by Tokens.content
    val type by Tokens.type
    val ownerId by Tokens.ownerId // must many to one
    companion object: EntityClass<Int, TokenDto>(Tokens)
}