package com.dudegenuine.app.model.role

import kotlinx.serialization.Serializable

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class RoleCreateRequest(
    val status: String,
    val userId: String
)
