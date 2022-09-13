package com.dudegenuine.app.repository.dependency

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.dudegenuine.app.model.security.AuthTokenClaim
import com.dudegenuine.app.model.security.AuthTokenConfig
import java.util.*

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class TokenDependency: ITokenDependency {
    override fun generate(
        config: AuthTokenConfig, vararg claims: AuthTokenClaim): String {
        val (mAudience, mIssuer, mExpiresIn, mSecret) = config
        var mToken = JWT.create()
            .withAudience(mAudience)
            .withIssuer(mIssuer)
            .withExpiresAt(Date(System.currentTimeMillis() + mExpiresIn))

        claims.forEach { claim ->
            mToken = mToken.withClaim(claim.key, claim.value)
        }
        return mToken.sign(Algorithm.HMAC256(mSecret))
    }
}