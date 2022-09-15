package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Conversations: UUIDTable("conversations") {
    val title = varchar("title", 40)
    val sessionId = varchar("session_id", 45)
    val createdAt = long("created_at")
    val updatedAt = long("updated_at").nullable()
    val deletedAt = long("deleted_at").nullable()
    val userId = uuid("user_id")
}
class ConversationDto(id: EntityID<UUID>): Entity<UUID>(id){
    var title by Conversations.title
    var sessionId by Conversations.sessionId
    var createdAt by Conversations.createdAt
    var updatedAt by Conversations.updatedAt
    var deletedAt by Conversations.deletedAt
    val participants by ParticipantDto referrersOn Participants.conversationId //val messageDto by MessageDto referrersOn Messages.conversationId

    companion object: EntityClass<UUID, ConversationDto>(Conversations)
}