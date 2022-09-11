package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.AuthDto
import com.dudegenuine.app.model.auth.AuthResponse

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IAuthMapper {
    fun asResponse(dto: AuthDto): AuthResponse
    //fun asDto(row: ResultRow): AuthDto
}