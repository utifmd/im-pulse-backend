package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.token.TokenRequest
import com.dudegenuine.app.model.token.TokenResponse

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface ITokenService {
    fun addToken(request: TokenRequest): TokenResponse
    fun removeToken(content: String): String
}