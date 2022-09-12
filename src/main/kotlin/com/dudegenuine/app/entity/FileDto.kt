package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Files: UUIDTable("files"){
    val type = varchar("type", 36)
    val data = binary("data")
}
class FileDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var type by Files.type
    var data by Files.data
    companion object: EntityClass<UUID, FileDto>(Files)
}