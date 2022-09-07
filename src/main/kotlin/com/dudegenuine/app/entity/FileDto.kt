package com.dudegenuine.app.entity

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.blob
import org.ktorm.schema.varchar

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface FileDto: Entity<FileDto> {
    var id: String
    var type: String
    var data: ByteArray
    companion object: Entity.Factory<FileDto>()
}
object Files: Table<FileDto>("files"){
    val id = varchar("id").primaryKey().bindTo { it.id }
    val type = varchar("type").bindTo { it.type }
    val data = blob("data").bindTo { it.data }
}