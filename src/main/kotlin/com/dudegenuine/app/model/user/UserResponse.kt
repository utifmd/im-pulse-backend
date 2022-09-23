package com.dudegenuine.app.model.user

import com.dudegenuine.app.entity.DeviceDto
import com.dudegenuine.app.entity.Devices
import com.dudegenuine.app.model.auth.AuthResponse
import com.dudegenuine.app.model.device.DeviceResponse
import com.dudegenuine.app.model.image.ImageResponse
import com.dudegenuine.app.model.role.RoleResponse
import com.dudegenuine.app.model.verifier.VerifierResponse
import kotlinx.serialization.Serializable

/**
 * Fri, 23 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class UserResponse(
    val id: String,
    val fullName: String,
    val pictureUrl: String?,
    val role: String?,
    val status: String?,
    val region: String?,
    val devices: List<String>,
    val createdAt: Long) {

    constructor(
        id: String,
        firstName: String,
        lastName: String,
        about: String?,
        status: String?,
        picture: ImageResponse?,
        region: String?,
        auth: AuthResponse?,
        role: RoleResponse?,
        verifiers: List<VerifierResponse>,
        devices: List<DeviceResponse>,
        createdAt: Long,
        updatedAt: Long?
    ): this(
        id = id,
        fullName = "$firstName $lastName",
        pictureUrl = picture?.url,
        role = role?.status,
        status = status,
        region = region,
        devices = devices.map(DeviceResponse::token),
        createdAt = createdAt,
    )
}
