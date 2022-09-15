package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.ConversationDto
import com.dudegenuine.app.entity.Conversations
import com.dudegenuine.app.model.conversation.Conversation
import com.dudegenuine.app.repository.contract.IConversationRepository
import com.dudegenuine.app.repository.validation.AlreadyExistException
import io.ktor.websocket.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ConversationRepository(database: Database): IConversationRepository {
    private val activeSessions = ConcurrentHashMap<String, List<Conversation>>()
    init {
        transaction { SchemaUtils.create(Conversations) }
    }
    override fun onSessionConnect(conversation: Conversation){
        val (mConverseId) = conversation
        val selectedSession = activeSessions[mConverseId]

        if(selectedSession != null) {
            val sessions = selectedSession.toMutableList()
            sessions.add(conversation)

            activeSessions[mConverseId] = sessions
        } else {
            activeSessions[mConverseId] = listOf(conversation)
        }
    }
    override suspend fun onSessionDisconnect(
        conversation: Conversation) = with(activeSessions) {
        val (mConverseId) = conversation

        if (containsKey(mConverseId)){
            get(mConverseId)
                ?. forEach{ it.socket.close() }
            remove(mConverseId)
        }
    }
    override suspend fun onSendBroadcast(
        converseId: String, encodedMessage: String) =
        activeSessions[converseId]
            ?. forEach{ it.socket.send(Frame.Text(encodedMessage)) }
            ?: Unit

    override fun createConverse(converseAndSessionId: Pair<String, String>) = transaction {
        val (mConverseId, mSessionId) = converseAndSessionId

        with(ConversationDto){
            findById(UUID.fromString(mConverseId)) ?: throw AlreadyExistException()
            new {
                title = mConverseId
                sessionId = mSessionId
                createdAt = System.currentTimeMillis()
                updatedAt = null
                deletedAt = null
            }
            Unit
        }
    }

}