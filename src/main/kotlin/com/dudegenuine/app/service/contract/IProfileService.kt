package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.profile.ProfileCreateRequest
import com.dudegenuine.app.model.profile.ProfileResponse
import com.dudegenuine.app.model.profile.ProfileUpdateRequest

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IProfileService{
    fun addProfile(request: ProfileCreateRequest): ProfileResponse
    fun patchProfile(request: ProfileUpdateRequest): ProfileResponse
}