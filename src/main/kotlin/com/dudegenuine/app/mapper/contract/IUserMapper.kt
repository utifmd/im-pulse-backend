package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.ImageDto
import com.dudegenuine.app.entity.RoleDto
import com.dudegenuine.app.entity.DeviceDto
import com.dudegenuine.app.entity.AuthDto
import com.dudegenuine.app.entity.UserDto
import com.dudegenuine.app.entity.VerifierDto
import com.dudegenuine.app.model.auth.AuthResponse
import com.dudegenuine.app.model.role.RoleResponse
import com.dudegenuine.app.model.device.DeviceResponse
import com.dudegenuine.app.model.image.ImageResponse
import com.dudegenuine.app.model.user.UserResponse
import com.dudegenuine.app.model.verifier.VerifierResponse

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IUserMapper {
    fun asUserCompleteResponse(dto: UserDto): UserResponse
    fun asUserHalfResponse(dto: UserDto): UserResponse
    fun asAuthResponse(dto: AuthDto): AuthResponse
    fun asRoleResponse(dto: RoleDto): RoleResponse
    fun asVerifierResponse(dto: VerifierDto): VerifierResponse
    fun asDeviceResponse(dto: DeviceDto): DeviceResponse
    fun asImageResponse(dto: ImageDto): ImageResponse
}