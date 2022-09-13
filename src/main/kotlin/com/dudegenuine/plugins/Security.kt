package com.dudegenuine.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.dudegenuine.app.model.security.AuthTokenConfig
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureSecurity() {
    val config: AuthTokenConfig by inject()

    authentication {
            jwt {
                val (mAudience, mIssuer, _, mSecret) = config
                realm = this@configureSecurity.environment.config.property("jwt.realm").getString()
                verifier(
                    JWT
                        .require(Algorithm.HMAC256(mSecret))
                        .withAudience(mAudience)
                        .withIssuer(mIssuer)
                        .build()
                )
                validate { credential ->
                    if (credential.payload.audience.contains(mAudience))
                        JWTPrincipal(credential.payload)
                    else null
                }
            }
        }

}
