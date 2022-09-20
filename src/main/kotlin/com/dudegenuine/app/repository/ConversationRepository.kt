package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.ConversationDto
import com.dudegenuine.app.entity.Conversations
import com.dudegenuine.app.entity.UserDto
import com.dudegenuine.app.mapper.contract.IConversationMapper
import com.dudegenuine.app.model.conversation.Conversation
import com.dudegenuine.app.model.conversation.ConversationResponse
import com.dudegenuine.app.repository.contract.IConversationRepository
import com.dudegenuine.app.repository.validation.AlreadyExistException
import com.dudegenuine.app.repository.validation.NotFoundException
import io.ktor.websocket.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ConversationRepository(
    private val mapper: IConversationMapper, database: Database): IConversationRepository {
    private val activeSessions = ConcurrentHashMap<String, WebSocketSession>() // key is every userId
    init {
        transaction { SchemaUtils.create(Conversations) }
    }
    override fun onSessionConnect(conversation: Conversation) {
        val (mFrom, mTo, mSocket) = conversation

        /* TODO conversation persistence graph
        * soeharto -> mega
        * mega -> soeharto
        * utifmd -> mega
        * mega -> utifmd
        * */
        activeSessions[mFrom] = mSocket
        if (activeSessions.containsKey(mTo)) return
        /*
        * initial connect
        * */
        activeSessions[mTo] = mSocket
        requireCreateConversation(conversation)
    }
    override suspend fun onSessionDisconnect(conversation: Conversation) {
        val (mFrom, _, mSocket) = conversation

        if (!activeSessions.containsKey(mFrom)) return

        mSocket.close()
        activeSessions.remove(mFrom)
    }
    override suspend fun onSendBroadcast(
        fromAndTo: Pair<String, String>, encodedMessage: String, targetOnly: Boolean) {
        val (mFrom, mTo) = fromAndTo

        activeSessions[mTo]
            ?.send(Frame.Text(encodedMessage))

        if(targetOnly) return

        activeSessions[mFrom]
            ?.send(Frame.Text(encodedMessage))
    }
    override fun requireCreateConversation(conversation: Conversation) = transaction {
        val (mUserId, mTargetUserId) = conversation

        with(ConversationDto){
            val dto = findById(UUID.fromString(mTargetUserId))
            if (dto != null) return@transaction

            new {
                title = "from $mUserId, to $mTargetUserId"
                createdAt = System.currentTimeMillis()
                updatedAt = null
                deletedAt = null
                targetUserDto = UserDto[UUID.fromString(mTargetUserId)]
                userId = UUID.fromString(mUserId)
            }
            Unit
        }
    }
    override fun readConversations(userId: String, pageAndSize: Pair<Long, Int>) = transaction {
        val (page, size) = pageAndSize

        ConversationDto
            .find{ Conversations.userId eq UUID.fromString(userId) }
            .limit(size, offset = page)
            .orderBy(Conversations.createdAt to SortOrder.ASC)
            .map(mapper::asResponse)
    }
    override fun deleteConversation(conversationId: String) = transaction {
        ConversationDto.findById(UUID.fromString(conversationId))
            ?.run {
                delete()
                id.value.toString()
            }
            ?: throw NotFoundException()
    }

    /*private fun filteredSession(
        from: String, to: String) = activeSessions.filterValues{ list ->
        list.any{ listOf(from, to).containsAll(listOf(it.from, it.to)) }
    }*/
    /*override fun onSessionConnect(conversation: Conversation){
        val (mFrom, mTo) = conversation

        if(activeSessions.containsKey(mFrom))

        *//*val initialKey = mFrom + mTo
        val currentSessions = filteredSession(mFrom, mTo)
        val selectedKey = currentSessions.keys.firstOrNull()
        if (selectedKey != null) {
            val sessions = currentSessions.values.firstOrNull()
                ?. toMutableList()
                ?: return
            sessions.add(conversation)

            println("selectedKey $selectedKey")
            activeSessions[selectedKey] = sessions
        } else {
            println("initialKey $initialKey")
            activeSessions[initialKey] = listOf(conversation)
        }*//*
    }*/
    /*override suspend fun onSessionDisconnect(conversation: Conversation){
        val (mFrom, mTo, mSocket) = conversation
        val sessions = filteredSession(mFrom, mTo)
        val selectedKey = sessions.keys.firstOrNull()

        if (selectedKey != null) {
            val currentSessions = sessions.values.firstOrNull()
                ?. toMutableList()
                ?: return

            mSocket.close()
            if (currentSessions.size <= 1)
                activeSessions.remove(selectedKey)

            currentSessions.remove(conversation)
            activeSessions[selectedKey] = currentSessions
        }
    }*/
    /*override suspend fun onSendBroadcast(
        fromAndTo: Pair<String, String>, encodedMessage: String) =
        filteredSession(fromAndTo.first, fromAndTo.second).values.firstOrNull()
            ?. forEach{ it.socket.send(Frame.Text(encodedMessage)) } ?: Unit
    override suspend fun onSendOnce(
            fromAndTo: Pair<String, String>, encodedMessage: String) =
            filteredSession(fromAndTo.first, fromAndTo.second).values.firstOrNull()
                ?. filter { it.from != fromAndTo.first }
                ?. forEach { it.socket.send(Frame.Text(encodedMessage)) } ?: Unit*/
}