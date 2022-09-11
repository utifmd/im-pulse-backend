package com.dudegenuine.app.model.image

import kotlinx.serialization.Serializable

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class ImageResponse(
    val url: String,
    val updatedAt: Long?
)