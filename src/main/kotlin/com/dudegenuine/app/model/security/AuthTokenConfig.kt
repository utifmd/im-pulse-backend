package com.dudegenuine.app.model.security

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
data class AuthTokenConfig(
    val audience: String,
    val issuer: String,
    val expiresIn: Long,
    val secret: String
)
