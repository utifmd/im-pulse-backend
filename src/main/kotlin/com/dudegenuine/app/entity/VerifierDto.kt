package com.dudegenuine.app.entity

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface VerifierDto: Entity<VerifierDto> {
    val id: String
    val type: String
    val payload: String
    val updatedAt: Long?
    val userId: String
}
object Verifications: Table<VerifierDto>("verifications"){
    val id = varchar("id").primaryKey().bindTo{ it.id }
    val email = varchar("type").bindTo{ it.type }
    val phone = varchar("payload").bindTo{ it.payload }
    val updatedAt = long("updated_at").bindTo{ it.updatedAt }
    val userId = varchar("user_id").bindTo{ it.userId }
}