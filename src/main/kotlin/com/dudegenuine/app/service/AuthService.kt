package com.dudegenuine.app.service

import com.dudegenuine.app.model.auth.AuthRegisterRequest
import com.dudegenuine.app.model.auth.AuthLoginRequest
import com.dudegenuine.app.model.auth.AuthUpdateRequest
import com.dudegenuine.app.repository.contract.IAuthRepository
import com.dudegenuine.app.service.contract.IAuthService

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class AuthService(
    private val repository: IAuthRepository): IAuthService {

    override fun listAuths(pageAndSize: Pair<Long, Int>) =
        repository.readAuths(pageAndSize)
    override fun findAuth(authId: String) =
        repository.readAuth(authId)
    override fun onSignIn(request: AuthLoginRequest) =
        repository.onSignIn(request)
    override fun onSignUp(request: AuthRegisterRequest) =
        repository.onSignUp(request)
    override fun deleteAuth(authId: String) =
        repository.deleteAuth(authId)
    override fun updateAuth(request: AuthUpdateRequest) =
        repository.updateAuth(request)
    /*override fun isUsernameExist(text: String) =
        repository.isUsernameExist(text)*/
}