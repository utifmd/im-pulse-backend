package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.ProfileDto
import com.dudegenuine.app.entity.Profiles
import com.dudegenuine.app.entity.UserDto
import com.dudegenuine.app.mapper.contract.IProfileMapper
import com.dudegenuine.app.model.profile.ProfileCreateRequest
import com.dudegenuine.app.model.profile.ProfileResponse
import com.dudegenuine.app.model.profile.ProfileUpdateRequest
import com.dudegenuine.app.repository.contract.IProfileRepository
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ProfileRepository(
    private val mapper: IProfileMapper): IProfileRepository {

    init {
        transaction{ SchemaUtils.create(Profiles) }
    }
    override fun createProfile(request: ProfileCreateRequest) = transaction {
        val (mAbout, mStatus, mRegion, mUserId) = request

        val dto = ProfileDto.new {
            about = mAbout
            status = mStatus
            region = mRegion
            userDto = UserDto[UUID.fromString(mUserId)]
            updatedAt = null
        }
        dto.let(mapper::asResponse)
    }
    override fun readProfile(profileId: String) = transaction {
        val dto = ProfileDto.findById(UUID.fromString(profileId)) ?: throw NotFoundException()

        dto.let(mapper::asResponse)
    }
    override fun updateProfile(request: ProfileUpdateRequest) = transaction {
        val (mId, mAbout, mStatus, mRegion) = request

        val dto = ProfileDto.findById(UUID.fromString(mId)) ?: throw NotFoundException()
        dto.apply {
            about = mAbout
            status = mStatus
            region = mRegion
            updatedAt = System.currentTimeMillis()
        }
        dto.let(mapper::asResponse)
    }
}