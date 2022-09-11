package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.UUID

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Profiles: UUIDTable("profiles"){
    val about = varchar("about", 127)
    val status = varchar("status", 38)
    val region = varchar("region", 38)
    val updatedAt = long("updated_at").nullable()
    val userId = reference("user_id", Users, ReferenceOption.CASCADE).nullable()
}
class ProfileDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var about by Profiles.about
    var status by Profiles.status
    var region by Profiles.region
    var updatedAt by Profiles.updatedAt

    /* as parent */
    val pictureDto by ImageDto optionalBackReferencedOn Images.profileId //val userDto by UserDto referrersOn Profiles.userId

    /* as child */
    var userDto by UserDto optionalReferencedOn Profiles.userId
    companion object: EntityClass<UUID, ProfileDto>(Profiles)
}