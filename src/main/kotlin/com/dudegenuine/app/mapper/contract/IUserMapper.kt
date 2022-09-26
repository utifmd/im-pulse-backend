package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.*
import com.dudegenuine.app.model.contact.ContactResponse
import com.dudegenuine.app.model.role.RoleResponse
import com.dudegenuine.app.model.device.DeviceResponse
import com.dudegenuine.app.model.image.ImageResponse
import com.dudegenuine.app.model.user.UserResponse

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IUserMapper {
    fun asUserResponse(dto: UserDto): UserResponse
    //fun asUserHalfResponse(dto: UserDto): UserResponse
    //fun asAuthResponse(dto: AuthDto): AuthResponse
    fun asRoleResponse(dto: RoleDto): RoleResponse
    fun asContact(dto: ContactDto): ContactResponse
    //fun asVerifierResponse(dto: VerifierDto): VerifierResponse
    fun asDeviceResponse(dto: DeviceDto): DeviceResponse
    fun asImageResponse(dto: ImageDto): ImageResponse
}