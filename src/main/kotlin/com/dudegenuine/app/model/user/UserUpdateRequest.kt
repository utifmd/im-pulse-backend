package com.dudegenuine.app.model.user

import kotlinx.serialization.Serializable

/**
 * Tue, 06 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class UserUpdateRequest(
    val currentUserId: String,
    val firstName: String,
    val lastName: String,
    val createdAt: Long,
    val updatedAt: Long
)
