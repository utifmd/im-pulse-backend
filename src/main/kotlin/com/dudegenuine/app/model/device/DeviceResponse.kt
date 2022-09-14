package com.dudegenuine.app.model.device

import kotlinx.serialization.Serializable

/**
 * Mon, 05 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class DeviceResponse(
    val token: String,
    val type: String,
)
