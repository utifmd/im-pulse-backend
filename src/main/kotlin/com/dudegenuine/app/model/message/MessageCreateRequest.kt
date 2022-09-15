package com.dudegenuine.app.model.message

import kotlinx.serialization.Serializable

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class MessageCreateRequest(
    val text: String,
    val type: String,
    val userId: String,
    val converseId: String
)
