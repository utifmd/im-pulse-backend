package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.ImageDto
import com.dudegenuine.app.entity.LevelDto
import com.dudegenuine.app.entity.TokenDto
import com.dudegenuine.app.entity.AuthDto
import com.dudegenuine.app.entity.UserDto
import com.dudegenuine.app.entity.VerifierDto
import com.dudegenuine.app.model.*
import java.text.DateFormat

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class UserMapper: IUserMapper {
    override fun formattedDate(timestamp: Long): String =
        DateFormat
            .getDateInstance(DateFormat.DEFAULT)
            .format(timestamp)
    override fun asUser(dto: UserDto) = with(dto){
        User(
            id = id,
            firstName = firstName,
            lastName = lastName,
            about = profileDto
                ?.about
                ?.ifBlank{ null },
            profileStatus = profileDto
                ?.status
                ?.ifBlank{ null },
            region = profileDto
                ?.region
                ?.ifBlank{ null },
            profilePicture = profileDto
                ?.picture
                ?.let(::asImage),
            auth = authDto
                ?.let(::asAuth),
            level = levelDto
                ?.status,
            verifier = verifier
                ?. map(::asVerifier)
                ?: emptyList(),
            tokens = tokens
                ?. map(::asToken)
                ?: emptyList(),
            createdAt = createdAt.let(::formattedDate),
            updatedAt = updatedAt
                ?.let(::formattedDate)
        )
    }
    override fun asUserOrNull(dto: UserDto?) = dto
        ?.let(::asUser)
    override fun asAuth(dto: AuthDto) = with(dto){
        Auth(
            email = email,
            username = username,
            password = password,
            lastPassword = lastPassword,
            updatedAt = updatedAt
                ?.let(::formattedDate),
        )
    }
    override fun asLevel(dto: LevelDto) = with(dto){
        Level(status = status)
    }
    override fun asVerifier(dto: VerifierDto) = with(dto){
        Verifier(
            type = type,
            payload = payload,
            updatedAt = updatedAt
        )
    }
    override fun asImage(dto: ImageDto) = with(dto){
        Image(url, updatedAt)
    }
    override fun asToken(dto: TokenDto) = with(dto){
        Token(content, type)
    }
}