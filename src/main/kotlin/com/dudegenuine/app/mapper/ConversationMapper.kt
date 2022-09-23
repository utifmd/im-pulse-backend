package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.ConversationDto
import com.dudegenuine.app.mapper.contract.IConversationMapper
import com.dudegenuine.app.mapper.contract.IParticipantMapper
import com.dudegenuine.app.mapper.contract.IUserMapper
import com.dudegenuine.app.model.conversation.ConversationResponse

/**
 * Tue, 20 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ConversationMapper(
    private val psnMapper: IParticipantMapper): IConversationMapper {
    override fun asResponse(dto: ConversationDto) = ConversationResponse(
        conversationId = dto.id.value.toString(),
        userId = dto.userId.value.toString(),
        createdAt = dto.createdAt,
        participants = dto.participants.map(psnMapper::asResponse) //.map(userMapper::asUserCensorResponse)
    )
}