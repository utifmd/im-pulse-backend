package com.dudegenuine.app.service

import com.dudegenuine.app.repository.IAuthRepository

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class AuthService(
    private val repository: IAuthRepository): IAuthService {

    override fun readAuth(pageAndSize: Pair<Long, Int>) =
        repository.getAuths(pageAndSize)
}