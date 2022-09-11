package com.dudegenuine.app.model.level

import kotlinx.serialization.Serializable

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class LevelCreateRequest(
    val status: String,
    val userId: String
)
