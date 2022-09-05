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
    val id: String
    val firstName: String
    val lastName: String
    val authDto: AuthDto?
    val profileDto: ProfileDto?
    val levelDto: LevelDto?
    val verifier: List<VerifierDto>?
    val tokens: List<TokenDto>?
    val createdAt: Long
    val updatedAt: Long? //companion object: Entity.Factory<UserDto>()
}
object Users: Table<UserDto>("users"){
    val id = varchar("id").primaryKey().bindTo{ it.id }
    val firstname = varchar("first_name").bindTo{ it.firstName }
    val lastName = varchar("last_name").bindTo{ it.lastName }
    val authId = varchar("authentication_id").references(Auths){ it.authDto }
    val profileId = varchar("profile_id").references(Profiles){ it.profileDto } //val verifyId = varchar("verification_id").references(Verifications){ it.verifiesDto }
    val levelId = varchar("level_id").references(Levels){ it.levelDto }
    val createdAt = long("created_at").bindTo{ it.createdAt }
    val updatedAt = long("updated_at").bindTo{ it.updatedAt }
}