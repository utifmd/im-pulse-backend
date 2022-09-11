package com.dudegenuine.app.model.profile

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
data class ProfileUpdateRequest(
    val id: String,
    val about: String,
    val status: String,
    val region: String
)
