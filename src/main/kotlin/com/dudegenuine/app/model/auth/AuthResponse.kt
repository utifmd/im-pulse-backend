package com.dudegenuine.app.model.auth

import kotlinx.serialization.Serializable

/**
 * Mon, 05 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class AuthResponse(
    val authId: String,
    val emailOrUsername: String,
    val password: String,
    val lastPassword: String,
    val updatedAt: Long?
)
