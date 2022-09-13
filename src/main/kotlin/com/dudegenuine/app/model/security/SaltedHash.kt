package com.dudegenuine.app.model.security

/**
 * Tue, 13 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
data class SaltedHash(
    val password: String,
    val salt: String
)
