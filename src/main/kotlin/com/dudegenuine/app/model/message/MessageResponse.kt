package com.dudegenuine.app.model.message

import kotlinx.serialization.Serializable

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class MessageResponse(
    val messageId: String,
    val text: String,
    val type: String,
    val createdAt: Long,
    val updatedAt: Long?,
    val deletedAt: Long?,
    val userId: String //, val conversationId: String
)
