package com.dudegenuine.app.model.file

import kotlinx.serialization.Serializable

/**
 * Mon, 05 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class Image(
    val url: String,
    val updatedAt: Long?
)
