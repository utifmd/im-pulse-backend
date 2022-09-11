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
            authResponse = authId.let(::asAuth),
            level = null, //levelDto?.status,
            verifierResponse = emptyList(), //verifier?.map(::asVerifier) ?: emptyList(),
            tokenResponses = emptyList(), //tokens?. map(::asToken) ?: emptyList(),
            createdAt = createdAt.let(Utils::formattedDate),
            updatedAt = updatedAt?.let(Utils::formattedDate)
        )
    }
    /*override fun asUserCompleteResponse(
        createRequest: UserCreateRequest) = with(createRequest){
        UserCompleteResponse(
            id = id,
            firstName = firstName,
            lastName = lastName,
            about = about,
            profileStatus = profileStatus,
            profilePicture = profilePicture,
            region = region,
            auth = auth,
            level = level,
            verifier = verifier,
            tokens = tokens,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }*/
    override fun asUserCensorResponse(dto: UserDto) = with(dto){
        UserCensorResponse(
            id = id.value.toString(),
            firstName = firstName,
            lastName = lastName,
            email = authId.email,
            username = authId.username,
            profilePictureUrl = null, //profileDto?.picture?.url,
            region = null, //profileDto?.region,
            level = level?.status,//level.joinToString { it.status }, //levelDto?.status,
            tokens = emptyList(), //tokens?.map(TokenDto::content) ?: emptyList(),
            createdAt = createdAt.let(Utils::formattedDate),
        )
    }
    /*override fun asDto(row: QueryRowSet) = UserDto().apply {
        val mAuth = AuthDto().apply {
            id = row[Auths.id] ?: EMPTY_STRING
            email = row[Auths.email] ?: EMPTY_STRING
            username = row[Auths.username] ?: EMPTY_STRING
            password = row[Auths.password] ?: EMPTY_STRING
            lastPassword = row[Auths.lastPassword] ?: EMPTY_STRING
            updatedAt = row[Auths.updatedAt] ?: System.currentTimeMillis()
        }
        val mPicture = ImageDto().apply {
            id = row[Images.id] ?: EMPTY_STRING
            url = row[Images.url] ?: EMPTY_STRING
            updatedAt = row[Images.updatedAt]
        }
        val mProfile = ProfileDto().apply {
            id = row[Profiles.id] ?: EMPTY_STRING
            about = row[Profiles.about] ?: EMPTY_STRING
            status = row[Profiles.status] ?: EMPTY_STRING
            region = row[Profiles.region] ?: EMPTY_STRING
            picture = mPicture
            updatedAt = row[Profiles.updatedAt]
        }
        val mLevel = LevelDto().apply {
            id = row[Levels.id] ?: EMPTY_STRING
            status = row[Levels.status] ?: EMPTY_STRING
            createdAt = row[Levels.createdAt]
        }
        id = row[Users.id] ?: EMPTY_STRING
        firstName = row[Users.firstName] ?: EMPTY_STRING
        lastName = row[Users.lastName] ?: EMPTY_STRING *//*authDto = getObject("authentication", AuthDto::class.java) profileDto = getObject("profile", ProfileDto::class.java) levelDto = getObject("level", LevelDto::class.java)*//*
        createdAt = row[Users.createdAt] ?: System.currentTimeMillis()
        updatedAt = row[Users.updatedAt]
        authDto = mAuth
        profileDto = mProfile
        levelDto = mLevel
    }*/
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