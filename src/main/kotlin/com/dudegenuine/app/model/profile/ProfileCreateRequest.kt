package com.dudegenuine.app.model.profile

import kotlinx.serialization.Serializable

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class ProfileCreateRequest(
    val about: String,
    val status: String,
    val region: String,
    val userId: String
)
