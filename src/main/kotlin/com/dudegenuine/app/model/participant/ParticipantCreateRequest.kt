package com.dudegenuine.app.model.participant

/**
 * Sat, 17 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
data class ParticipantCreateRequest(
    val userId: String,
    val conversationId: String,
    val role: String
)
