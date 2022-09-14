package com.dudegenuine.app.model.auth

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
data class AuthUpdateRequest(
    val currentAuthId: String,
    val emailOrUsername: String,
    val password: String,
    val lastPassword: String
)
