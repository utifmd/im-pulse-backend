package com.dudegenuine.app.model.user

import kotlinx.serialization.Serializable

/**
 * Tue, 06 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class UserCreateRequest(
    val firstName: String,
    val lastName: String,
    val contactId: String,
    val authId: String

    /*val profileId: String?,
    val levelId: String?*/
)
