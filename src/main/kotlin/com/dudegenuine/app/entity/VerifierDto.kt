package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.UUID

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Verifications: UUIDTable("verifications"){
    val type = varchar("type", 127)
    val payload = varchar("payload", 36)
    val updatedAt = long("updated_at").nullable()
    val userId = reference("user_id", Users, ReferenceOption.CASCADE)
}
class VerifierDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var type by Verifications.type
    var payload by Verifications.payload
    var updatedAt by Verifications.updatedAt
    var userId by Verifications.userId
    companion object: EntityClass<UUID, VerifierDto>(Verifications)
}