package com.dudegenuine.app.entities

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class AuthDto(id: EntityID<Int>): Entity<Int>(id) {
    var authId by Auths.authId
    var email by Auths.email
    var username by Auths.username
    var password by Auths.password
    var lastPassword by Auths.lastPassword
    var updatedAt: Long? by Auths.updatedAt

    companion object: EntityClass<Int, AuthDto>(Auths)
}
object Auths: IntIdTable("auth"){
    val authId = varchar("auth_id", 48).uniqueIndex()
    val email = varchar("email", 127)
    val username = varchar("username", 127)
    val password = varchar("password", 127)
    val lastPassword = varchar("last_password", 127)
    val updatedAt = long("updated_at").nullable()
}