package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.conversation.Conversation
import com.dudegenuine.app.model.conversation.ConversationResponse

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IConversationRepository {
    fun requireCreateConversation(conversation: Conversation)
    fun deleteConversation(conversationId: String): String
    fun readConversations(userId: String, pageAndSize: Pair<Long, Int>): List<ConversationResponse>
    fun onSessionConnect(conversation: Conversation)
    suspend fun onSendBroadcast(fromAndTo: Pair<String, String>, encodedMessage: String, targetOnly: Boolean = false)
    suspend fun onSessionDisconnect(conversation: Conversation)
}