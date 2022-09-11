package com.dudegenuine.app.service

import com.dudegenuine.app.model.profile.ProfileCreateRequest
import com.dudegenuine.app.model.profile.ProfileUpdateRequest
import com.dudegenuine.app.repository.contract.IProfileRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.IProfileService

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ProfileService(
    private val repository: IProfileRepository): IProfileService {

    override fun addProfile(request: ProfileCreateRequest) = try {
        repository.createProfile(request)
    } catch (e: Exception){
        throw BadRequestException(e.localizedMessage)
    }

    override fun patchProfile(request: ProfileUpdateRequest) = try {
        repository.updateProfile(request)
    } catch (e: Exception){
        throw BadRequestException(e.localizedMessage)
    }
}