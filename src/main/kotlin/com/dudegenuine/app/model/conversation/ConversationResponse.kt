package com.dudegenuine.app.model.conversation

import com.dudegenuine.app.model.participant.ParticipantResponse
import kotlinx.serialization.Serializable

/**
 * Tue, 20 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class ConversationResponse(
    val conversationId: String,
    val createdAt: Long,
    val participants: List<ParticipantResponse>,
    val userId: String
)