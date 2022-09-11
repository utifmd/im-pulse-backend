package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.auth.AuthCreateRequest
import com.dudegenuine.app.model.auth.AuthResponse
import com.dudegenuine.app.model.auth.AuthUpdateRequest

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IAuthService {
    fun findAuth(authId: String): AuthResponse
    fun createAuth(request: AuthCreateRequest): AuthResponse
    fun deleteAuth(authId: String): String
    fun updateAuth(request: AuthUpdateRequest): AuthResponse
    fun listAuths(pageAndSize: Pair<Long, Int>): List<AuthResponse>
    fun isUsernameExist(text: String): Boolean
}