package com.dudegenuine.app.repository.dependency

import com.dudegenuine.app.model.security.AuthTokenClaim
import com.dudegenuine.app.model.security.AuthTokenConfig

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface ITokenDependency {
    fun generate(config: AuthTokenConfig, vararg claims: AuthTokenClaim): String
}