package com.dudegenuine.app.model.user

import com.dudegenuine.app.model.auth.AuthResponse
import com.dudegenuine.app.model.device.DeviceResponse
import com.dudegenuine.app.model.image.ImageResponse
import com.dudegenuine.app.model.role.RoleResponse
import com.dudegenuine.app.model.verifier.VerifierResponse
import kotlinx.serialization.Serializable

/**
 * Fri, 23 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
object User {
    @Serializable
    data class HalfResponse(
        val id: String,
        val fullName: String,
        val pictureUrl: String?,
        val role: String?,
        val status: String?,
        val region: String?,
        val devices: List<String>,
        val createdAt: Long
    )
    @Serializable
    data class CompleteResponse(
        val id: String,
        val firstName: String,
        val lastName: String,
        val about: String?,
        val status: String?,
        val picture: ImageResponse?,
        val region: String?,
        val auth: AuthResponse?,
        val role: RoleResponse?,
        val verifiers: List<VerifierResponse>,
        val devices: List<DeviceResponse>,
        val createdAt: Long,
        val updatedAt: Long?
    )
}
 **/
