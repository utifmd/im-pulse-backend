package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.ProfileDto
import com.dudegenuine.app.mapper.contract.IProfileMapper
import com.dudegenuine.app.model.profile.ProfileResponse

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ProfileMapper: IProfileMapper {
    override fun asResponse(dto: ProfileDto) = ProfileResponse(
        about = dto.about,
        status = dto.status,
        region = dto.region,
        pictureUrl = dto.pictureDto?.url,
        pictureUpdatedAt = dto.pictureDto?.updatedAt,
        updatedAt = dto.updatedAt,
    )
}