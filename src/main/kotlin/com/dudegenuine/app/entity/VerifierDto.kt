package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Verifications: IntIdTable("verifications"){
    val verifierId = varchar("verifier_id", 127)
    val type = varchar("type", 127)
    val payload = varchar("payload", 36)
    val updatedAt = long("updated_at")
    val userId = varchar("user_id", 127)
}
class VerifierDto(id: EntityID<Int>): Entity<Int>(id) {
    val verifierId by Verifications.verifierId
    val type by Verifications.type
    val payload by Verifications.payload
    val updatedAt by Verifications.updatedAt
    val userId by Verifications.userId
    companion object: EntityClass<Int, VerifierDto>(Verifications)
}