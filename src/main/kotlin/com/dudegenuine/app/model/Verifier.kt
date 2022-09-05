package com.dudegenuine.app.model

import kotlinx.serialization.Serializable

/**
 * Sun, 04 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class Verifier( //val id: String,
    val type: String,
    val payload: String,
    val updatedAt: Long?
)
