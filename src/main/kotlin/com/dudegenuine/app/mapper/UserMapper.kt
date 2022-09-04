package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.user.UserDto
import com.dudegenuine.app.model.User
import java.text.DateFormat

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class UserMapper: IUserMapper {
    private fun formattedDate(timestamp: Long): String = DateFormat
        .getDateInstance(DateFormat.DEFAULT)
        .format(timestamp)

    override fun asUser(dto: UserDto) = User(
        id = dto.id,
        firstName = dto.firstName,
        lastName = dto.lastName,
        password = dto.authDto
            ?.password
            ?.ifBlank{ null },
        about = dto.profileDto
            ?.about
            ?.ifBlank{ null },
        profileStatus = dto.profileDto
            ?.status
            ?.ifBlank{ null },
        region = dto.profileDto
            ?.region
            ?.ifBlank{ null },
        createdAt = formattedDate(dto.createdAt),
        updatedAt = dto.updatedAt?.let(::formattedDate)
    )
}