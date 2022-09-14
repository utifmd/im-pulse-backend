package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.ContactDto
import com.dudegenuine.app.mapper.contract.IContactMapper
import com.dudegenuine.app.model.contact.ContactResponse

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ContactMapper: IContactMapper {
    override fun asResponse(dto: ContactDto) = ContactResponse(
        email = dto.email,
        phone = dto.phone,
        username = dto.username,
        address = dto.address
    )
}