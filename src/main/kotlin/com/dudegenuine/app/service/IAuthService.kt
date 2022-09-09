package com.dudegenuine.app.service

import com.dudegenuine.app.model.auth.AuthResponse

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IAuthService {
    fun readAuth(pageAndSize: Pair<Long, Int>): List<AuthResponse>
}