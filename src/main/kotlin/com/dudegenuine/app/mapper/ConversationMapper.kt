package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.ConversationDto
import com.dudegenuine.app.mapper.contract.IConversationMapper
import com.dudegenuine.app.mapper.contract.IUserMapper
import com.dudegenuine.app.model.conversation.ConversationResponse

/**
 * Tue, 20 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ConversationMapper(
    private val userMapper: IUserMapper): IConversationMapper {
    override fun asResponse(dto: ConversationDto) = ConversationResponse(
        conversationId = dto.id.value.toString(),
        title = dto.title,
        userId = dto.userId.toString(),
        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt,
        deletedAt = dto.deletedAt,
        targetUser = dto.targetUserDto
            .let(userMapper::asUserCensorResponse),
    )
}