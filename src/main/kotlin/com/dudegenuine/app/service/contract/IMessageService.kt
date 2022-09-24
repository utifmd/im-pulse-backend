package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.message.MessageCreateRequest
import com.dudegenuine.app.model.message.MessageResponse
import com.dudegenuine.app.model.message.MessageUpdateRequest

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IMessageService{
    fun addMessage(request: MessageCreateRequest): MessageResponse
    fun putMessage(request: MessageUpdateRequest): MessageResponse
    fun pagedMessages(conversationsId: String, pageAndSize: Pair<Long, Int>): List<MessageResponse>
}