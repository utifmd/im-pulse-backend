package com.dudegenuine.app.entity

import com.dudegenuine.app.entity.ParticipantDto.Companion.referrersOn
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Conversations: UUIDTable("conversations") { //val title = varchar("title", 40) //val sessionId = varchar("session_id", 45) /*val updatedAt = long("updated_at").nullable() val deletedAt = long("deleted_at").nullable()*/
    val createdAt = long("created_at")
    val targetUserId = reference("target_user_id", Users)
    val userId = reference("user_id", Users)
}
class ConversationDto(id: EntityID<UUID>): Entity<UUID>(id) { //var title by Conversations.title
    var createdAt by Conversations.createdAt /*var updatedAt by Conversations.updatedAt var deletedAt by Conversations.deletedAt*/ //val participant by ParticipantDto backReferencedOn Participants.conversationId //val messageDto by MessageDto referrersOn Messages.conversationId /*var targetUserDto by UserDto referencedOn Conversations.targetUserId var userDto by UserDto referencedOn Conversations.userId*/
    var targetUserId by Conversations.targetUserId
    var userId by Conversations.userId

    val participants by ParticipantDto referrersOn Participants.conversationId
    companion object: EntityClass<UUID, ConversationDto>(Conversations)
}