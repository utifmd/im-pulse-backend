package com.dudegenuine.app.model.conversation

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
data class ConversationRequest(
    val converseId: String?,
    val from: String,
    val to: String
)