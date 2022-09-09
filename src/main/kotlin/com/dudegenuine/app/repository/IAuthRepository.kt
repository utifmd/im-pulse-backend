package com.dudegenuine.app.repository

import com.dudegenuine.app.entities.AuthDto
import com.dudegenuine.app.model.auth.AuthCreateRequest
import com.dudegenuine.app.model.auth.AuthResponse
import com.dudegenuine.app.model.auth.AuthUpdateRequest

/**
 * Tue, 06 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IAuthRepository {
    fun getAuths(pageAndSize: Pair<Long, Int>): List<AuthResponse>
    fun postAuth(createRequest: AuthCreateRequest)
    fun putAuth(updateRequest: AuthUpdateRequest)
}