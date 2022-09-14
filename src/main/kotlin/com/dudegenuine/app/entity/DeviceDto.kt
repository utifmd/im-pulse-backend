package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.UUID

/**
 * Mon, 05 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Devices: UUIDTable("devices") {
    val token = varchar("token", 120)
    val type = varchar("type", 25)
    val updatedAt = long("updatedAt").nullable()
    val userId = reference("user_id", Users, ReferenceOption.CASCADE)
}
class DeviceDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var token by Devices.token
    var type by Devices.type
    var updatedAt by Devices.updatedAt
    var userDto by UserDto referencedOn Devices.userId
    companion object: EntityClass<UUID, DeviceDto>(Devices)
}