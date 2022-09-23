package com.dudegenuine.app.service

import com.dudegenuine.app.model.message.MessageCreateRequest
import com.dudegenuine.app.model.message.MessageResponse
import com.dudegenuine.app.model.message.MessageUpdateRequest
import com.dudegenuine.app.repository.contract.IMessageRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.InternalErrorException
import com.dudegenuine.app.service.contract.IMessageService
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class MessageService(
    private val repository: IMessageRepository): IMessageService {

    override fun addMessage(request: MessageCreateRequest) =
        try { repository.createMessage(request) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }

    override fun putMessage(request: MessageUpdateRequest) =
        try { repository.updateMessage(request) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }

    override fun listMessages(conversationsId: String, pageAndSize: Pair<Long, Int>) =
        try { repository.listMessage(conversationsId, pageAndSize) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
}