package com.dudegenuine.app.model.role

import kotlinx.serialization.Serializable

/**
 * Mon, 05 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class RoleResponse(
    val status: String,
    val updatedAt: Long?
)
