package com.dudegenuine.app.entity

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

/**
 * Mon, 05 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface LevelDto: Entity<LevelDto> {
    val id: String
    val status: String
    val createdAt: Long?
}

object Levels: Table<LevelDto>("levels"){
    val id = varchar("id").primaryKey().bindTo { it.id }
    val status = varchar("status").bindTo { it.status }
    val createdAt = long("created_at").bindTo { it.createdAt }
}