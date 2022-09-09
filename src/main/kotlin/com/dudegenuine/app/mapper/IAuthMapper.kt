package com.dudegenuine.app.mapper

import com.dudegenuine.app.entities.AuthDto
import com.dudegenuine.app.model.auth.AuthResponse
import org.jetbrains.exposed.sql.ResultRow

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IAuthMapper {
    fun asResponse(dto: AuthDto): AuthResponse
    //fun asDto(row: ResultRow): AuthDto
}