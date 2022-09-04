package com.dudegenuine.app.entity.user

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface VerifyDto: Entity<VerifyDto> {
    val id: String
    val type: String
    val payload: String
    val updatedAt: Long?
    val userId: String
}
object Verifications: Table<VerifyDto>("verifications"){
    val id = varchar("id").primaryKey().bindTo{ it.id }
    val email = varchar("type").bindTo{ it.type }
    val phone = varchar("payload").bindTo{ it.payload }
    val updatedAt = long("updatedAt").bindTo{ it.updatedAt }
    val userId = varchar("userId").bindTo{ it.userId }
}