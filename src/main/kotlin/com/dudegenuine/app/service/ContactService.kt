package com.dudegenuine.app.service

import com.dudegenuine.app.model.contact.ContactCreateRequest
import com.dudegenuine.app.model.contact.ContactUpdateRequest
import com.dudegenuine.app.repository.contract.IContactRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.InternalErrorException
import com.dudegenuine.app.service.contract.IContactService

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ContactService(
    private val repository: IContactRepository): IContactService {

    override fun addContact(request: ContactCreateRequest) =
        try{ repository.createContact(request) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }

    override fun putContact(request: ContactUpdateRequest) =
        try{ repository.updateContact(request) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
}