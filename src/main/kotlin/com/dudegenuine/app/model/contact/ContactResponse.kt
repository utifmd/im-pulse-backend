package com.dudegenuine.app.model.contact

import kotlinx.serialization.Serializable

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class ContactResponse(
    val contactId: String,
    val email: String,
    val phone: String?,
    val username: String,
    val address: String?
)
