package com.dudegenuine.app.model.conversation

import io.ktor.websocket.*

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
data class Conversation(
    val from: String,
    val to: String,
    val socket: WebSocketSession
)
