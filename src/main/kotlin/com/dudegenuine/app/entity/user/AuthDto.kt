package com.dudegenuine.app.entity.user

import org.ktorm.entity.Entity
import org.ktorm.schema.*
import java.sql.Date
import java.time.Instant
import java.time.LocalDateTime

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface AuthDto: Entity<AuthDto> {
    val id: String
    val email: String
    val username: String
    val password: String
    val lastPassword: String
    val updatedAt: Long?
}

object Auths: Table<AuthDto>("authentications"){
    val id = varchar("id").primaryKey().bindTo { it.id }
    val email = varchar("email").bindTo { it.email }
    val username = varchar("username").bindTo { it.username }
    val password = varchar("password").bindTo { it.password }
    val lastPassword = varchar("last_password").bindTo { it.lastPassword }
    // val verification_id = varchar("verification_id").references(Verifications){ it.verification }
    val updatedAt = long("updated_at").bindTo { it.updatedAt }
}