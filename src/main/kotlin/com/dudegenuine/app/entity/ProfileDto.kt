package com.dudegenuine.app.entity

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface ProfileDto: Entity<ProfileDto> {
    var id: String
    var about: String
    var status: String
    var region: String
    var picture: ImageDto?
    var updatedAt: Long?

    companion object: Entity.Factory<ProfileDto>()
}
object Profiles: Table<ProfileDto>("profiles"){
    val id = varchar("id").primaryKey().bindTo { it.id }
    val about = varchar("about").bindTo { it.about }
    val status = varchar("status").bindTo { it.status }
    val region = varchar("region").bindTo { it.region }
    val imageId = varchar("image_id").references(Images){ it.picture }
    val updatedAt = long("updated_at").bindTo { it.updatedAt }
}