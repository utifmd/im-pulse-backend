package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.user.UserDto
import com.dudegenuine.app.model.User

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IUserMapper {
    fun asUser(dto: UserDto): User
}