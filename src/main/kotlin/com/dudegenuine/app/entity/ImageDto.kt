package com.dudegenuine.app.entity

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

/**
 * Mon, 05 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface ImageDto: Entity<ImageDto> {
    val id: String
    val url: String
    val updatedAt: Long?
}

object Images: Table<ImageDto>("images"){
    val id = varchar("id").primaryKey().bindTo { it.id }
    val url = varchar("url").bindTo { it.url }
    val updatedAt = long("updated_at").bindTo { it.updatedAt }
}