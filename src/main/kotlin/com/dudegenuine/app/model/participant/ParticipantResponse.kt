package com.dudegenuine.app.model.participant

import com.dudegenuine.app.model.user.UserResponse
import kotlinx.serialization.Serializable

/**
 * Mon, 19 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class ParticipantResponse(
    val role: String,
    val isRead: Boolean,
    val createdAt: Long,
    val user: UserResponse
)