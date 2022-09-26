package com.dudegenuine.app.model.file

import kotlinx.serialization.Serializable

/**
 * Mon, 26 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class FileResponse(
    val id: String,
    val type: String,
    val role: String
)
