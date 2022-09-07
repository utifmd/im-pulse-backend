package com.dudegenuine.app.entity

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface AuthDto: Entity<AuthDto> {
    var id: String
    var email: String
    var username: String
    var password: String
    var lastPassword: String
    var updatedAt: Long?
    companion object: Entity.Factory<AuthDto>()
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