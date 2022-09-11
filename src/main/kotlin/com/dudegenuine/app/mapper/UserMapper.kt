package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.*
import com.dudegenuine.app.mapper.contract.IUserMapper
import com.dudegenuine.app.model.auth.AuthResponse
import com.dudegenuine.app.model.file.Image
import com.dudegenuine.app.model.level.LevelResponse
import com.dudegenuine.app.model.token.TokenResponse
import com.dudegenuine.app.model.user.UserCensorResponse
import com.dudegenuine.app.model.user.UserCompleteResponse
import com.dudegenuine.app.model.verifier.VerifierResponse
import com.dudegenuine.app.repository.common.Utils

/**
 * Sat, 03 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class UserMapper: IUserMapper {
    override fun asUserCompleteResponse(dto: UserDto) = with(dto){
        UserCompleteResponse(
            id = id.value.toString(),
            firstName = firstName,
            lastName = lastName,
            about = null, //profileDto?.about?.ifBlank{ null },
            profileStatus = null, //profileDto?.status?.ifBlank{ null },
            region = null, //profileDto?.region?.ifBlank{ null },
            profilePicture = null,//profileDto?.picture?.let(::asImage),
            authResponse = authDto.let(::asAuth),
            level = null, //levelDto?.status,
            verifierResponse = emptyList(), //verifier?.map(::asVerifier) ?: emptyList(),
            tokenResponses = emptyList(), //tokens?. map(::asToken) ?: emptyList(),
            createdAt = createdAt.let(Utils::formattedDate),
            updatedAt = updatedAt?.let(Utils::formattedDate)
        )
    }
    override fun asUserCensorResponse(dto: UserDto) = with(dto){
        UserCensorResponse(
            id = id.value.toString(),
            firstName = firstName,
            lastName = lastName,
            email = authDto.email,
            username = authDto.username,
            profilePictureUrl = profileDto?.pictureDto?.url,
            region = profileDto?.region,
            level = levelDto?.status,
            status = profileDto?.status,
            tokens = emptyList(),
            createdAt = createdAt.let(Utils::formattedDate),
        )
    }
    override fun asUserCompleteResponseOrNull(dto: UserDto?) = dto
        ?.let(::asUserCompleteResponse)

    override fun asUserCensorResponseOrNull(dto: UserDto?) = dto
        ?.let(::asUserCensorResponse)

    override fun asAuth(dto: AuthDto) = with(dto){
        AuthResponse(
            email = email,
            username = username,
            password = password,
            lastPassword = lastPassword,
            updatedAt = updatedAt
                ?.let(Utils::formattedDate),
        )
    }
    override fun asLevel(dto: LevelDto) = with(dto){
        LevelResponse(status = status)
    }
    override fun asVerifier(dto: VerifierDto) = with(dto){
        VerifierResponse(
            type = type,
            payload = payload,
            updatedAt = updatedAt
        )
    }
    override fun asImage(dto: ImageDto) = with(dto){
        Image(url, updatedAt)
    }
    override fun asToken(dto: TokenDto) = with(dto){
        TokenResponse(content, type)
    }
}