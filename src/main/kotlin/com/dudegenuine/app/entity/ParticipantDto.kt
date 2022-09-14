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
    val type = varchar("type", 25)
    val createdAt = long("created_at")
    val updatedAt = long("updated_at").nullable()
    val userId = uuid("user_id")
    val conversationId = reference("converse_id", Conversations, ReferenceOption.CASCADE)
}
class ParticipantDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var type by Participants.type
    var createdAt by Participants.createdAt
    var updatedAt by Participants.updatedAt
    var userId by Participants.userId
    var conversationDto by ConversationDto referencedOn Participants.conversationId

    companion object: EntityClass<UUID, ParticipantDto>(Participants)
}