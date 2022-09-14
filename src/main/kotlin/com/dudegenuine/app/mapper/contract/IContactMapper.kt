package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.ContactDto
import com.dudegenuine.app.model.contact.ContactResponse

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IContactMapper {
    fun asResponse(dto: ContactDto): ContactResponse
}