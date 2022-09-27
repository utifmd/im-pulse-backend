package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.*
import com.dudegenuine.app.mapper.contract.IUserMapper
import com.dudegenuine.app.model.auth.AuthResponse
import com.dudegenuine.app.model.contact.ContactResponse
import com.dudegenuine.app.model.role.RoleResponse
import com.dudegenuine.app.model.device.DeviceResponse
import com.dudegenuine.app.model.image.ImageResponse
import com.dudegenuine.app.model.user.UserResponse
import com.dudegenuine.app.model.verifier.VerifierResponse

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class UserMapper: IUserMapper {
    override fun asUserResponse(dto: UserDto) = with(dto){
        UserResponse(
            id = id.value.toString(),
            firstName = firstName,
            lastName = lastName,
            contact = contactDto.let(::asContact),
            createdAt = createdAt,
            updatedAt = updatedAt,
            about = profileDto?.about,
            status = profileDto?.status,
            region = profileDto?.region,
            picture = profileDto?.pictureDto?.let(::asImageResponse),
            role = roleDto?.let(::asRoleResponse),
            devices = devices.map(::asDeviceResponse)
        )
    }
    override fun asImageResponse(dto: ImageDto) = with(dto){
        ImageResponse(url, role, updatedAt)
    }
    override fun asDeviceResponse(dto: DeviceDto) = with(dto){
        DeviceResponse(token, type, updatedAt)
    }
    override fun asRoleResponse(dto: RoleDto) = with(dto){
        RoleResponse(status = current, updatedAt = updatedAt)
    }

    override fun asContact(dto: ContactDto) = ContactResponse(
        contactId = dto.id.value.toString(),
        email = dto.email,
        phone = dto.phone,
        username = dto.username,
        address = dto.address
    )
    /*override fun asUserHalfResponse(dto: UserDto) = with(dto){
        UserResponse(
            id = id.value.toString(),
            fullName = "$firstName $lastName",
            pictureUrl = profileDto?.pictureDto?.url,
            region = profileDto?.region,
            role = roleDto?.current,
            status = profileDto?.status,
            devices = devices.map(DeviceDto::token),
            createdAt = createdAt,
        )
    }
    override fun asAuthResponse(dto: AuthDto) = with(dto){
        AuthResponse(
            authId = id.value.toString(),
            emailOrUsername = emailOrUsername,
            password = password,
            lastPassword = lastPassword,
            updatedAt = updatedAt,
        )
    }
    override fun asVerifierResponse(dto: VerifierDto) = with(dto){
        VerifierResponse(
            type = type,
            payload = payload,
            updatedAt = updatedAt
        )
    }*/
}