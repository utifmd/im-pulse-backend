package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.MessageDto
import com.dudegenuine.app.entity.Messages
import com.dudegenuine.app.mapper.contract.IMessageMapper
import com.dudegenuine.app.model.message.MessageCreateRequest
import com.dudegenuine.app.model.message.MessageUpdateRequest
import com.dudegenuine.app.repository.common.Utils
import com.dudegenuine.app.repository.contract.IMessageRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class MessageRepository(
    private val mapper: IMessageMapper): IMessageRepository {
    init {
        transaction { SchemaUtils.create(Messages) }
    }
    override fun listMessage(
        conversationId: String, pageAndSize: Pair<Long, Int>) = transaction {
        val (page, size) = pageAndSize
        val mConversationId = conversationId.let(Utils::uuidOrNull) ?: throw BadRequestException()

        MessageDto.find{ Messages.conversationId eq mConversationId }
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
            userId = mUserId?.let(Utils::uuidOrNull)
            conversationId = mConverseId?.let(Utils::uuidOrNull)
        }
        dto.let(mapper::asResponse)
    }

    override fun updateMessage(request: MessageUpdateRequest) = transaction {
        val (mMessageId, mText, mType) = request
        val messageId = mMessageId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val messages = MessageDto.findById(messageId) ?: throw NotFoundException()
        val dto = messages.apply {
            text = mText
            type = mType
            updatedAt = System.currentTimeMillis()
        }
        dto.let(mapper::asResponse)
    }
}