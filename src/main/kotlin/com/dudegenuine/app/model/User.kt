package com.dudegenuine.app.model

import kotlinx.serialization.Serializable

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val password: String?,
    val about: String?,
    val profileStatus: String?,
    val region: String?,
    val createdAt: String,
    val updatedAt: String?
)
