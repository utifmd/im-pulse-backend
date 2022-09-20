package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.MessageDto
import com.dudegenuine.app.entity.Messages
import com.dudegenuine.app.mapper.contract.IMessageMapper
import com.dudegenuine.app.model.message.MessageCreateRequest
import com.dudegenuine.app.model.message.MessageResponse
import com.dudegenuine.app.model.message.MessageUpdateRequest
import com.dudegenuine.app.repository.contract.IMessageRepository
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class MessageRepository(
    private val mapper: IMessageMapper, database: Database): IMessageRepository {
    init {
        transaction { SchemaUtils.create(Messages) }
    }
    override fun listMessage(
        conversationId: String, pageAndSize: Pair<Long, Int>) = transaction {
        val (page, size) = pageAndSize

        MessageDto.find{ Messages.conversationId eq UUID.fromString(conversationId) }
            .limit(size, offset = page)
            .orderBy(Messages.createdAt to SortOrder.DESC)
            .map(mapper::asResponse)
    }

    override fun createMessage(request: MessageCreateRequest) = transaction {
        val (mText, mType, mUserId, mConverseId) = request
        val dto = MessageDto.new {
            text = mText
            type = mType
            createdAt = System.currentTimeMillis()
            updatedAt = null
            deletedAt = null
            userId = mUserId?.let(UUID::fromString)
            conversationId = mConverseId?.let(UUID::fromString)
        }
        dto.let(mapper::asResponse)
    }

    override fun updateMessage(request: MessageUpdateRequest) = transaction {
        val (mMessageId, mText, mType, mCreatedAt, _, mDeletedAt) = request
        val messages = MessageDto.findById(UUID.fromString(mMessageId)) ?: throw NotFoundException()
        val dto = messages.apply {
            text = mText
            type = mType
            createdAt = mCreatedAt
            updatedAt = System.currentTimeMillis()
            deletedAt = mDeletedAt
        }
        dto.let(mapper::asResponse)
    }
}