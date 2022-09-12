package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.token.TokenRequest
import com.dudegenuine.app.model.token.TokenResponse

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface ITokenRepository {
    fun createToken(request: TokenRequest): TokenResponse
    fun deleteToken(content: String): String
}