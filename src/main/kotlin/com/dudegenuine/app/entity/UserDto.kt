package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.UUID

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Users: UUIDTable("users"){
    val firstName = varchar("first_name", 127).nullable()
    val lastName = varchar("last_name", 127).nullable()
    val createdAt = long("created_at")
    val updatedAt = long("updated_at").nullable()

    val contactId = reference("contact_id", Contacts)
    val authId = reference("auth_id", Auths, ReferenceOption.CASCADE)
}
class UserDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var firstName by Users.firstName
    var lastName by Users.lastName
    var createdAt by Users.createdAt
    var updatedAt by Users.updatedAt

    /* this class as parent */
    val profileDto by ProfileDto optionalBackReferencedOn Profiles.userId
    val roleDto by RoleDto optionalBackReferencedOn Roles.userId
    val devices by DeviceDto referrersOn Devices.userId
    /*val verifiers by VerifierDto referrersOn Verifications.userId //val tokenDto by TokenDto via Tokens*/

    /* this class as child */
    var contactDto by ContactDto referencedOn Users.contactId
    var authId by Users.authId //var authDto by AuthDto referencedOn Users.authId
    companion object: EntityClass<UUID, UserDto>(Users)
}