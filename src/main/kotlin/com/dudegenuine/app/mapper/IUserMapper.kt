package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.ImageDto
import com.dudegenuine.app.entity.LevelDto
import com.dudegenuine.app.entity.TokenDto
import com.dudegenuine.app.entity.AuthDto
import com.dudegenuine.app.entity.UserDto
import com.dudegenuine.app.entity.VerifierDto
import com.dudegenuine.app.model.*

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IUserMapper {
    fun asUser(dto: UserDto): User
    fun asUserOrNull(dto: UserDto?): User?
    fun asAuth(dto: AuthDto): Auth
    fun asLevel(dto: LevelDto): Level
    fun asVerifier(dto: VerifierDto): Verifier
    fun asToken(dto: TokenDto): Token
    fun asImage(dto: ImageDto): Image
    fun formattedDate(timestamp: Long): String
}