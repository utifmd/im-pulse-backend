package com.dudegenuine.app.model.message

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
data class MessageUpdateRequest(
    val messageId: String,
    val text: String,
    val type: String,
    val createdAt: Long,
    val updatedAt: Long?,
    val deletedAt: Long?
)
