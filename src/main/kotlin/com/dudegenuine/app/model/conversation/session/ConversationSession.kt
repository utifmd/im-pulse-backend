package com.dudegenuine.app.model.conversation.session

import io.ktor.websocket.*

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
data class ConversationSession(
    val converseIdOrNull: String?,
    val from: String,
    val to: String,
    val socket: WebSocketSession
)
