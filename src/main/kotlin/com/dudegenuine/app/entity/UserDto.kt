package com.dudegenuine.app.entity

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface UserDto: Entity<UserDto> {
    var id: String
    var firstName: String
    var lastName: String
    var authDto: AuthDto?
    var profileDto: ProfileDto?
    var levelDto: LevelDto?
    var verifier: List<VerifierDto>?
    var tokens: List<TokenDto>?
    var createdAt: Long
    var updatedAt: Long?
    companion object: Entity.Factory<UserDto>()
}
object Users: Table<UserDto>("users"){
    val id = varchar("id").primaryKey().bindTo{ it.id }
    val firstName = varchar("first_name").bindTo{ it.firstName }
    val lastName = varchar("last_name").bindTo{ it.lastName }
    val authId = varchar("authentication_id").references(Auths){ it.authDto }
    val profileId = varchar("profile_id").references(Profiles){ it.profileDto }
    val levelId = varchar("level_id").references(Levels){ it.levelDto }
    val createdAt = long("created_at").bindTo{ it.createdAt }
    val updatedAt = long("updated_at").bindTo{ it.updatedAt }
}