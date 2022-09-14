package com.dudegenuine.app.model.device

import kotlinx.serialization.Serializable

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class DeviceRequest(
    val token: String,
    val type: String,
    val userId: String
)