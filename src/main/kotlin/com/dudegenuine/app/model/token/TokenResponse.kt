package com.dudegenuine.app.model.token

import kotlinx.serialization.Serializable

/**
 * Mon, 05 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class TokenResponse(
    val content: String,
    val type: String,
)
