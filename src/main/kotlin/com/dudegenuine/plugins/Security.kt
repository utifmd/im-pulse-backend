package com.dudegenuine.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.dudegenuine.app.controller.PATH_CHAT_CONVERSE
import com.dudegenuine.app.model.security.AuthTokenConfig
import com.dudegenuine.app.model.conversation.ConversationRequest
import com.dudegenuine.app.repository.validation.BadRequestException
import io.ktor.server.application.*
import io.ktor.server.application.ApplicationCallPipeline.ApplicationPhase.Plugins
import io.ktor.server.request.*
import io.ktor.server.sessions.*
import org.koin.ktor.ext.inject

fun Application.configureSecurity() {
    val config: AuthTokenConfig by inject()

    configureJwt(config)
    configureSession()
    configureInterception()
}
private fun Application.configureInterception() {
    intercept(Plugins){
        if (
            call.request.path().contains(PATH_CHAT_CONVERSE) &&
            call.sessions.get<ConversationRequest>() == null
        ) {
            val converseId = call.parameters["converseId"]
            val from = call.parameters["from"] ?: throw BadRequestException()
            val to = call.parameters["to"] ?: throw BadRequestException()

            call.sessions.set(ConversationRequest(converseId, from, to))
        }
    }
}
private fun Application.configureSession() {
    install(Sessions){
        cookie<ConversationRequest>("CONVERSE_SESSION"){
            cookie.path = PATH_CHAT_CONVERSE
        }
    }
}
private fun Application.configureJwt(config: AuthTokenConfig){
    authentication {
        jwt {
            val (mAudience, mIssuer, mRealm, _, mSecret) = config
            realm = mRealm
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