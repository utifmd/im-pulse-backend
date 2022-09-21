package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.ConversationDto
import com.dudegenuine.app.entity.Conversations
import com.dudegenuine.app.entity.UserDto
import com.dudegenuine.app.mapper.contract.IConversationMapper
import com.dudegenuine.app.model.conversation.session.ConversationSession
import com.dudegenuine.app.repository.contract.IConversationRepository
import com.dudegenuine.app.repository.validation.NotFoundException
import io.ktor.websocket.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.or
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
    override fun onSessionConnect(conversationSession: ConversationSession): String {
        val (_, mFrom, mTo, mSocket) = conversationSession

        activeSessions[mFrom] = mSocket
        if (!activeSessions.containsKey(mTo)) activeSessions[mTo] = mSocket

        return requireCreateConversation(conversationSession)
    }
    override suspend fun onSessionDisconnect(conversationSession: ConversationSession) {
        val (_, mFrom, _, mSocket) = conversationSession

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
    override fun requireCreateConversation(conversationSession: ConversationSession) = transaction {
        val (mConverseIdOrNull, mUserId, mTargetUserId) = conversationSession

        if (mConverseIdOrNull != null) return@transaction mConverseIdOrNull
        val conv = ConversationDto.new {
            title = "Private chat"
            createdAt = System.currentTimeMillis()
            updatedAt = null
            deletedAt = null
            targetUserDto = UserDto[UUID.fromString(mTargetUserId)]
            userId = UUID.fromString(mUserId)
        }
        conv.id.value.toString()
    }
    override fun readConversations(
        userId: String, pageAndSize: Pair<Long, Int>) = transaction {
        val (page, size) = pageAndSize
        val meaningId = UUID.fromString(userId)

        ConversationDto
            .find{
                (Conversations.userId eq meaningId or
                        (Conversations.targetUserId eq meaningId)) }
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
}