package com.dudegenuine.app.model.user

import com.dudegenuine.app.model.auth.AuthResponse
import com.dudegenuine.app.model.file.Image
import com.dudegenuine.app.model.device.DeviceResponse
import com.dudegenuine.app.model.verifier.VerifierResponse
import kotlinx.serialization.Serializable

/**
 * Tue, 06 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class UserCompleteResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val about: String?,
    val profileStatus: String?,
    val profilePicture: Image?,
    val region: String?,
    val authResponse: AuthResponse?,
    val level: String?,
    val verifierResponse: List<VerifierResponse>,
    val deviceRespons: List<DeviceResponse>,
    val createdAt: String,
    val updatedAt: String?
)
