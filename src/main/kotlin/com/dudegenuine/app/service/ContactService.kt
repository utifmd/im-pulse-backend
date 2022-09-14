package com.dudegenuine.app.service

import com.dudegenuine.app.model.contact.ContactCreateRequest
import com.dudegenuine.app.model.contact.ContactUpdateRequest
import com.dudegenuine.app.repository.contract.IContactRepository
import com.dudegenuine.app.service.contract.IContactService

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ContactService(
    private val repository: IContactRepository): IContactService {

    override fun addContact(request: ContactCreateRequest) =
        repository.createContact(request)

    override fun putContact(request: ContactUpdateRequest) =
        repository.updateContact(request)
}