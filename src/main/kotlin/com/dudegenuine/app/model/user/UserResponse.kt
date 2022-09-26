package com.dudegenuine.app.model.user

import com.dudegenuine.app.model.contact.ContactResponse
import com.dudegenuine.app.model.device.DeviceResponse
import com.dudegenuine.app.model.image.ImageResponse
import com.dudegenuine.app.model.role.RoleResponse
import com.dudegenuine.app.model.verifier.VerifierResponse
import kotlinx.serialization.Serializable

/**
 * Mon, 26 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class UserResponse(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val contact: ContactResponse,
    val about: String?,
    val status: String?,
    val picture: ImageResponse?,
    val region: String?,
    val role: RoleResponse?, //val verifiers: List<VerifierResponse>?,
    val devices: List<DeviceResponse>,
    val createdAt: Long,
    val updatedAt: Long?
)
