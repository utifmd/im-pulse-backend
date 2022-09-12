package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.TokenDto
import com.dudegenuine.app.model.token.TokenResponse

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface ITokenMapper {
    fun asResponse(dto: TokenDto): TokenResponse
}