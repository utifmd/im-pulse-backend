package com.dudegenuine.app.repository

import com.dudegenuine.app.model.auth.AuthCreateRequest
import com.dudegenuine.app.model.auth.AuthUpdateRequest

/**
 * Tue, 06 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IAuthRepository {
    fun postAuth(createRequest: AuthCreateRequest)
    fun putAuth(updateRequest: AuthUpdateRequest)
}