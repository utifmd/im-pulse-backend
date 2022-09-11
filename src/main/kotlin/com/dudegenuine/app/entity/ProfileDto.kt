package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Profiles: UUIDTable("profiles"){
    val about = varchar("about", 127)
    val status = varchar("status", 36)
    val region = varchar("region", 36)
    val imageId = varchar("image_id", 127)
    val updatedAt = long("updated_at")
}
class ProfileDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var about by Profiles.about
    var status by Profiles.status
    var region by Profiles.region
    var updatedAt by Profiles.updatedAt

    val picture by ImageDto optionalReferrersOn Images.profileId
    companion object: EntityClass<UUID, ProfileDto>(Profiles)
}