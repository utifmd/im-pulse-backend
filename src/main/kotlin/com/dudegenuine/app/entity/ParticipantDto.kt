package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.UUID

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Participants: UUIDTable("participants"){
    val role = varchar("role", 25)
    val isRead = bool("is_read")
    val createdAt = long("created_at")
    val userId = reference("user_id", Users).nullable()
    val conversationId = reference("converse_id", Conversations, ReferenceOption.CASCADE)
}
class ParticipantDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var role by Participants.role
    var isRead by Participants.isRead
    var createdAt by Participants.createdAt
    var userDto by UserDto optionalReferencedOn Participants.userId //Participants.userId
    var conversationId by Participants.conversationId

    companion object: EntityClass<UUID, ParticipantDto>(Participants)
}