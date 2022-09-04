package com.dudegenuine.app.entity.user

import com.dudegenuine.app.entity.user.Auths.bindTo
import com.dudegenuine.app.entity.user.Profiles.bindTo
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface ProfileDto: Entity<ProfileDto>{
    val id: String
    val about: String
    val status: String
    val region: String
    val updatedAt: Long?
}
object Profiles: Table<ProfileDto>("profiles"){
    val id = varchar("id").primaryKey().bindTo { it.id }
    val about = varchar("about").bindTo { it.about }
    val status = varchar("status").bindTo { it.status }
    val region = varchar("region").bindTo { it.region }
    val updatedAt = long("updated_at").bindTo { it.updatedAt }
}