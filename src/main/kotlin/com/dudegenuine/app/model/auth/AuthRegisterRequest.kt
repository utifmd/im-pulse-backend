package com.dudegenuine.app.model.auth

import kotlinx.serialization.Serializable

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class AuthRegisterRequest(
    val email: String,
    val username: String,
    val password: String
)