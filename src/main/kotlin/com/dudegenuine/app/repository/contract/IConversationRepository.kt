package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.conversation.session.ConversationSession
import com.dudegenuine.app.model.conversation.ConversationResponse
import com.dudegenuine.app.model.participant.ParticipantCreateRequest
import org.jetbrains.exposed.sql.Transaction

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IConversationRepository {
    //fun requireCreateConversation(conversationSession: ConversationSession): String
    fun deleteConversation(conversationId: String): String
    fun readConversations(userId: String, pageAndSize: Pair<Long, Int>): List<ConversationResponse>
    fun onSessionConnect(
        session: ConversationSession/*,
        onRequireCreateParticipants: Transaction.(
            ParticipantCreateRequest,
            ParticipantCreateRequest
        ) -> Unit*/
    ): String
    suspend fun onSendBroadcast(fromAndTo: Pair<String, String>, encodedMessage: String, targetOnly: Boolean = false)
    suspend fun onSessionDisconnect(conversationSession: ConversationSession)
}