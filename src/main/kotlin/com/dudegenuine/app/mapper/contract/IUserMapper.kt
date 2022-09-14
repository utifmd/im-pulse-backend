package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.ImageDto
import com.dudegenuine.app.entity.RoleDto
import com.dudegenuine.app.entity.DeviceDto
import com.dudegenuine.app.entity.AuthDto
import com.dudegenuine.app.entity.UserDto
import com.dudegenuine.app.entity.VerifierDto
import com.dudegenuine.app.model.auth.AuthResponse
import com.dudegenuine.app.model.file.Image
import com.dudegenuine.app.model.role.RoleResponse
import com.dudegenuine.app.model.device.DeviceResponse
import com.dudegenuine.app.model.user.UserCensorResponse
import com.dudegenuine.app.model.user.UserCompleteResponse
import com.dudegenuine.app.model.verifier.VerifierResponse

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IUserMapper {
    fun asUserCompleteResponse(dto: UserDto): UserCompleteResponse
    //fun asUserCompleteResponse(createRequest: UserCreateRequest): UserCompleteResponse
    fun asUserCensorResponse(dto: UserDto): UserCensorResponse
    //fun asDto(row: QueryRowSet): UserDto
    fun asUserCompleteResponseOrNull(dto: UserDto?): UserCompleteResponse?
    fun asUserCensorResponseOrNull(dto: UserDto?): UserCensorResponse?
    fun asAuth(dto: AuthDto): AuthResponse
    fun asLevel(dto: RoleDto): RoleResponse
    fun asVerifier(dto: VerifierDto): VerifierResponse
    fun asToken(dto: DeviceDto): DeviceResponse
    fun asImage(dto: ImageDto): Image
}