package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.ContactDto
import com.dudegenuine.app.entity.Contacts
import com.dudegenuine.app.entity.Users
import com.dudegenuine.app.mapper.contract.IContactMapper
import com.dudegenuine.app.model.contact.ContactCreateRequest
import com.dudegenuine.app.model.contact.ContactResponse
import com.dudegenuine.app.model.contact.ContactUpdateRequest
import com.dudegenuine.app.repository.contract.IContactRepository
import com.dudegenuine.app.repository.validation.AlreadyExistException
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ContactRepository(
    private val mapper: IContactMapper): IContactRepository {
    init {
        transaction { SchemaUtils.create(Contacts) }
    }
    override fun createContact(request: ContactCreateRequest) = transaction {
        val (mEmail, mPhone, mUsername, mAddress) = request
        val contacts = ContactDto.find{
            Contacts.username.eq(mUsername) or Contacts.email.eq(mEmail) or Contacts.phone.eq(mPhone)
        }
        if(!contacts.empty()) throw AlreadyExistException()

        val dto = ContactDto.new {
            email = mEmail
            phone = mPhone
            username = mUsername
            address = mAddress
        }
        dto.let(mapper::asResponse)
    }
    override fun updateContact(request: ContactUpdateRequest) = transaction {
        val (mContactId, mEmail, mPhone, mUsername, mAddress) = request
        val contacts = ContactDto.findById(UUID.fromString(mContactId)) ?: throw NotFoundException()

        contacts.apply {
            email = mEmail
            phone = mPhone
            username = mUsername
            address = mAddress
        }
        contacts.let(mapper::asResponse)
    }
    override fun isUsernameExist(request: String) = transaction {
        val auths = ContactDto.find{ Contacts.username eq request }

        !auths.empty()
    }
}