package com.dudegenuine.app.service

import com.dudegenuine.app.model.auth.AuthRegisterRequest
import com.dudegenuine.app.model.auth.AuthLoginRequest
import com.dudegenuine.app.model.auth.AuthUpdateRequest
import com.dudegenuine.app.repository.contract.IAuthRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.InternalErrorException
import com.dudegenuine.app.service.contract.IAuthService

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class AuthService(
    private val repository: IAuthRepository): IAuthService {

    override fun listAuths(pageAndSize: Pair<Long, Int>) =
        try { repository.readAuths(pageAndSize) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    override fun findAuth(authId: String) =
        try { repository.readAuth(authId) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    override fun onSignIn(request: AuthLoginRequest) =
        try { repository.onSignIn(request) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    override fun onSignUp(request: AuthRegisterRequest) =
        try { repository.onSignUp(request) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    override fun deleteAuth(authId: String) =
        try { repository.deleteAuth(authId) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    override fun updateAuth(request: AuthUpdateRequest) =
        try { repository.updateAuth(request) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    /*override fun isUsernameExist(text: String) =
        repository.isUsernameExist(text)*/
}