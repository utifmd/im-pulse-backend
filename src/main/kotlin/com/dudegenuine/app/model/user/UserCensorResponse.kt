package com.dudegenuine.app.model.user

import kotlinx.serialization.Serializable

/**
 * Tue, 06 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class UserCensorResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String?,
    val username: String?,
    val profilePictureUrl: String?,
    val region: String?,
    val level: String?,
    val status: String?,
    val tokens: List<String>,
    val createdAt: String,
)
