package com.dudegenuine.app.model.verifier

import kotlinx.serialization.Serializable

/**
 * Sun, 04 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class VerifierResponse(
    val type: String,
    val payload: String,
    val updatedAt: Long?
)
