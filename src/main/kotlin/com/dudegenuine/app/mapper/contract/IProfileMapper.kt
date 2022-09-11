package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.ProfileDto
import com.dudegenuine.app.model.profile.ProfileResponse

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IProfileMapper {
    fun asResponse(dto: ProfileDto): ProfileResponse
}