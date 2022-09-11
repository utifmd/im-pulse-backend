package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.profile.ProfileCreateRequest
import com.dudegenuine.app.model.profile.ProfileResponse
import com.dudegenuine.app.model.profile.ProfileUpdateRequest

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IProfileRepository {
    fun createProfile(request: ProfileCreateRequest): ProfileResponse
    fun readProfile(profileId: String): ProfileResponse
    fun updateProfile(request: ProfileUpdateRequest): ProfileResponse
}