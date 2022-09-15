package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.message.MessageCreateRequest
import com.dudegenuine.app.model.message.MessageResponse
import com.dudegenuine.app.model.message.MessageUpdateRequest

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IMessageRepository {
    fun listMessage(pageAndSize: Pair<Long, Int>): List<MessageResponse>
    fun createMessage(request: MessageCreateRequest): MessageResponse
    fun updateMessage(request: MessageUpdateRequest): MessageResponse
}