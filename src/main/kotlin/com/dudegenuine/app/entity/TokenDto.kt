package com.dudegenuine.app.entity

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.varchar

/**
 * Mon, 05 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface TokenDto: Entity<TokenDto> {
    val id: String
    val content: String
    val type: String
    val ownerId: String
}

object Tokens: Table<TokenDto>("tokens") {
    val id = varchar("id").primaryKey().bindTo { it.id }
    val content = varchar("content").bindTo { it.content }
    val type = varchar("type").bindTo { it.type }
    val ownerId = varchar("owner_id").bindTo { it.ownerId }
}