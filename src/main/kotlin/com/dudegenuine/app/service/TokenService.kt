package com.dudegenuine.app.service

import com.dudegenuine.app.model.token.TokenRequest
import com.dudegenuine.app.repository.contract.ITokenRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.ITokenService

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class TokenService(
    private val repository: ITokenRepository): ITokenService {

    override fun addToken(request: TokenRequest) =
        try { repository.createToken(request) } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
    override fun removeToken(content: String) =
        try { repository.deleteToken(content) } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
}