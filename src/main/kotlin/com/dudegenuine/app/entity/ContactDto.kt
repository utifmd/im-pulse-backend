package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Contacts: UUIDTable("contacts"){
    val email = varchar("email", 127)
    val phone = varchar("phone", 28)
    val username = varchar("username", 45)
    val address = varchar("address", 127)
}
class ContactDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var email by Contacts.email
    var phone by Contacts.phone
    var username by Contacts.username
    var address by Contacts.address

    companion object: EntityClass<UUID, ContactDto>(Contacts)
}