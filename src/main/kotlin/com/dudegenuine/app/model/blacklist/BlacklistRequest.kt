package com.dudegenuine.app.model.blacklist

import kotlinx.serialization.Serializable

/**
 * Fri, 23 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class BlacklistRequest(
    val targetUserId: String,
    val userId: String
)