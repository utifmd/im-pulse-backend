package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.TokenDto
import com.dudegenuine.app.mapper.contract.ITokenMapper
import com.dudegenuine.app.model.token.TokenResponse

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class TokenMapper: ITokenMapper {
    override fun asResponse(dto: TokenDto) = TokenResponse(
        content = dto.content,
        type = dto.type
    )
}