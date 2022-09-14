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
object Messages: UUIDTable("messages"){
    val text = varchar("text", 255)
    val type = varchar("type", 25)
    val createdAt = long("created_at")
    val updatedAt = long("updated_at").nullable()
    val deletedAt = long("deleted_at").nullable()
    val userId = uuid("user_id")
    val conversationId = uuid("converse_id")
}
class MessageDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var text by Messages.text
    var type by Messages.type
    var createdAt by Messages.createdAt
    var updatedAt by Messages.updatedAt
    var deletedAt by Messages.deletedAt
    var userId by Messages.userId
    var conversationId by Messages.conversationId

    companion object: EntityClass<UUID, MessageDto>(Messages)
}