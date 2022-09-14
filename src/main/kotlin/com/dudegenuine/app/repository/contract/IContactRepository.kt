package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.contact.ContactCreateRequest
import com.dudegenuine.app.model.contact.ContactUpdateRequest
import com.dudegenuine.app.model.contact.ContactResponse

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IContactRepository {
    fun createContact(request: ContactCreateRequest): ContactResponse
    fun updateContact(request: ContactUpdateRequest): ContactResponse
    fun isUsernameExist(request: String): Boolean
}