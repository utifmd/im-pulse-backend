package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.contact.ContactCreateRequest
import com.dudegenuine.app.model.contact.ContactResponse
import com.dudegenuine.app.model.contact.ContactUpdateRequest

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IContactService {
    fun addContact(request: ContactCreateRequest): ContactResponse
    fun putContact(request: ContactUpdateRequest): ContactResponse
}