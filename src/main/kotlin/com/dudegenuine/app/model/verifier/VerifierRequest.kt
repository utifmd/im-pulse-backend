package com.dudegenuine.app.model.verifier

import kotlinx.serialization.Serializable

/**
 * Wed, 28 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class VerifierRequest(
    val type: String,
    val payload: String,
    val userId: String
)
