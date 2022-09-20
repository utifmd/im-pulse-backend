package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.ConversationDto
import com.dudegenuine.app.model.conversation.ConversationResponse

/**
 * Tue, 20 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IConversationMapper {
    fun asResponse(dto: ConversationDto): ConversationResponse
}