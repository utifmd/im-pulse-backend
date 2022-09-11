package com.dudegenuine.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.UUID

/**
 * Mon, 05 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Images: UUIDTable("images"){
    val url = varchar("url", 225)
    val updatedAt = long("updated_at")

    val profileId = reference("profile_id", Profiles, ReferenceOption.CASCADE).nullable()
}
class ImageDto(id: EntityID<UUID>): Entity<UUID>(id) {
    var url by Images.url
    var updatedAt by Images.updatedAt

    var profileDto by ProfileDto optionalReferencedOn Images.profileId
    companion object: EntityClass<UUID, ImageDto>(Images)
}