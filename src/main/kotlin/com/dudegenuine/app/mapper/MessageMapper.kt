package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.MessageDto
import com.dudegenuine.app.mapper.contract.IMessageMapper
import com.dudegenuine.app.model.message.MessageResponse

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class MessageMapper: IMessageMapper {
    override fun asResponse(dto: MessageDto) = MessageResponse(
        id = dto.id.value.toString(),
        text = dto.text,
        type = dto.type,
        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt,
        deletedAt = dto.deletedAt,
        userId = dto.userId.toString() //, conversationId = dto.conversationId.toString(),
    )
}