package com.dudegenuine.app.mapper

import com.dudegenuine.app.entities.AuthDto
import com.dudegenuine.app.entities.Auths
import com.dudegenuine.app.model.auth.AuthResponse
import com.dudegenuine.app.repository.common.Utils
import org.jetbrains.exposed.sql.ResultRow

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class AuthMapper: IAuthMapper {

    override fun asResponse(dto: AuthDto) = with(dto){
        AuthResponse(
            email = email,
            username = username,
            password = password,
            lastPassword = lastPassword,
            updatedAt = updatedAt?.let(Utils::formattedDate),
        )
    }
    /*override fun asDto(row: ResultRow): AuthDto {
        AuthDto(row[Auths.id]) {
            authId = row[Auths.authId]
            email = row[Auths.email]
            username = row[Auths.username]
            password = row[Auths.password]
            lastPassword = row[Auths.lastPassword]
            updatedAt = row[Auths.updatedAt]
        }
    }*/
}