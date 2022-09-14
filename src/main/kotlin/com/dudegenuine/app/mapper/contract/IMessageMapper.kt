package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.MessageDto
import com.dudegenuine.app.model.message.MessageResponse

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IMessageMapper {
    fun asResponse(dto: MessageDto): MessageResponse
}