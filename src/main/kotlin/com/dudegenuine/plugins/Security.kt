package com.dudegenuine.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.dudegenuine.app.model.security.AuthTokenConfig
import com.dudegenuine.app.model.conversation.session.ConverseSession
import com.dudegenuine.app.repository.validation.BadRequestException
import io.ktor.server.application.*
import io.ktor.server.application.ApplicationCallPipeline.ApplicationPhase.Plugins
import io.ktor.server.sessions.*
import io.ktor.util.*
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
    install(Sessions){
        cookie<ConverseSession>("CONVERSE_SESSION")
    }
    intercept(Plugins){
        with(call){
            if (sessions.get<ConverseSession>() == null){

                val converseId = parameters["converseId"]
                    ?: throw BadRequestException("converseId")

                sessions.set(ConverseSession(converseId))
            }
        }
    }
}
