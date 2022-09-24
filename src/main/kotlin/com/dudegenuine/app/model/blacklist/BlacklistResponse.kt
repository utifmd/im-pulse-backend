package com.dudegenuine.app.model.blacklist

import com.dudegenuine.app.model.user.UserResponse
import kotlinx.serialization.Serializable

/**
 * Fri, 23 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class BlacklistResponse(
    val blacklistId: String,
    val targetUserId: String,
    val targetUser: UserResponse?,
    val userId: String
)