package com.dudegenuine.app.model.file

import kotlinx.serialization.Serializable

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class File(
    val id: String,
    val type: String,
    val data: ByteArray
)
