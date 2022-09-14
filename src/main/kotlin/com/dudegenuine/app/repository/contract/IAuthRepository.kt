package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.auth.AuthRegisterRequest
import com.dudegenuine.app.model.auth.AuthLoginRequest
import com.dudegenuine.app.model.auth.AuthResponse
import com.dudegenuine.app.model.auth.AuthUpdateRequest

/**
 * Tue, 06 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IAuthRepository {
    fun onSignUp(request: AuthRegisterRequest)
    fun onSignIn(request: AuthLoginRequest): String
    fun readAuth(authId: String): AuthResponse
    fun updateAuth(request: AuthUpdateRequest): AuthResponse
    fun deleteAuth(authId: String): String
    fun readAuths(pageAndSize: Pair<Long, Int>): List<AuthResponse>
    companion object{
        const val PASSWORD_MISMATCH = "password mismatch."
    }
}