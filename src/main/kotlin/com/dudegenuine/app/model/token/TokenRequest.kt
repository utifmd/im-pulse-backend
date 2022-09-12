package com.dudegenuine.app.model.token

import kotlinx.serialization.Serializable

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class TokenRequest(
    val content: String,
    val type: String,
    val userId: String
)