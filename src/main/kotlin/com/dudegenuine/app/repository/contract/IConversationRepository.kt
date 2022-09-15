package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.conversation.Conversation

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IConversationRepository {
    fun createConverse(converseAndSessionId: Pair<String, String>)
    fun onSessionConnect(conversation: Conversation)
    suspend fun onSendBroadcast(converseId: String, encodedMessage: String)
    suspend fun onSessionDisconnect(conversation: Conversation)
    //fun sendToConversation(payload: String, conversation: Conversation)
}