package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Auths: UUIDTable("auths"){
    val email = varchar("email", 127)
    val username = varchar("username", 127)
    val password = varchar("password", 127)
    val lastPassword = varchar("last_password", 127)
    val updatedAt = long("updated_at").nullable()
}
class AuthDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var email by Auths.email
    var username by Auths.username
    var password by Auths.password
    var lastPassword by Auths.lastPassword
    var updatedAt: Long? by Auths.updatedAt
    companion object: EntityClass<UUID, AuthDto>(Auths) //val users by UserDto optionalReferrersOn Users.authId
}