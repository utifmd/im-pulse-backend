package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Files: IntIdTable("files"){
    val fileId = varchar("file_id", 127)
    val type = varchar("type", 36)
    val data = binary("data")
}
class FileDto(id: EntityID<Int>): Entity<Int>(id) {
    var fileId by Files.fileId
    var type by Files.type
    var data by Files.data
    companion object: EntityClass<Int, FileDto>(Files)
}