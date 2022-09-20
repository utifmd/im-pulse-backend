package com.dudegenuine.app.model.conversation

import com.dudegenuine.app.model.user.UserCensorResponse
import kotlinx.serialization.Serializable

/**
 * Tue, 20 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class ConversationResponse(
    val title: String,
    val createdAt: Long,
    val updatedAt: Long?,
    val deletedAt: Long?,
    val targetUser: UserCensorResponse,
    val userId: String,
)
