package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.*
import com.dudegenuine.app.mapper.contract.IConversationMapper
import com.dudegenuine.app.model.conversation.session.ConversationSession
import com.dudegenuine.app.model.participant.ParticipantCreateRequest
import com.dudegenuine.app.repository.common.Utils
import com.dudegenuine.app.repository.contract.IConversationRepository
import com.dudegenuine.app.repository.contract.IParticipantRepository
import com.dudegenuine.app.repository.contract.IParticipantRepository.Companion.RECIPIENT
import com.dudegenuine.app.repository.contract.IParticipantRepository.Companion.SENDER
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.NotFoundException
import io.ktor.websocket.*
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.concurrent.ConcurrentHashMap

/**
 * Thu, 15 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
// TODO: all responses createdAt model change to long type
class ConversationRepository(
    private val mapper: IConversationMapper,
    private val participantRepository: IParticipantRepository): IConversationRepository {
    private val activeSessions = ConcurrentHashMap<String, WebSocketSession>() // key is every userId
    init {
        transaction { SchemaUtils.create(Conversations) }
    }
    override fun onSessionConnect(session: ConversationSession): String {
        val (_, mFrom, mTo, mSocket) = session

        activeSessions[mFrom] = mSocket
        if (!activeSessions.containsKey(mTo)) activeSessions[mTo] = mSocket

        return transaction {
            val converseId = requireCreateConversation(session)
            val sender = ParticipantCreateRequest(mFrom, converseId, SENDER)
            val recipient = ParticipantCreateRequest(mTo, converseId, RECIPIENT)

            participantRepository.requireCreateParticipants(sender, recipient)//onRequireCreateParticipants(sender, recipient)
            converseId
        }
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
    private fun requireCreateConversation(conversationSession: ConversationSession): String /*= transaction*/ {
        val (mConverseIdOrNull, mUserId, mTargetUserId) = conversationSession
        val myUserId = mUserId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val myTargetUserId = mTargetUserId.let(Utils::uuidOrNull) ?: throw BadRequestException()

        if (mConverseIdOrNull != null) /*return@transaction*/ return mConverseIdOrNull // got from chat list

        /* prevent race condition */
        val dto = ConversationDto.find{
            ((Conversations.userId eq myUserId) and (Conversations.targetUserId eq myTargetUserId)) or
                    ((Conversations.userId eq myTargetUserId) and (Conversations.targetUserId eq myUserId)) }

        // [race condition] both create new in the sametime
        if (!dto.empty()) /*return@transaction*/ return dto.firstOrNull()?.id?.value.toString()

        val conv = ConversationDto.new {
            createdAt = System.currentTimeMillis() //participants = ParticipantDto[UUID.fromString("")] isRead = false targetUserDto = UserDto[UUID.fromString(mTargetUserId)] userDto = UserDto[UUID.fromString(mUserId)]
            targetUserId = EntityID(myTargetUserId, Users)
            userId = EntityID(myUserId, Users)
        }
        return conv.id.value.toString()
    }
    override fun readConversations(
        userId: String, pageAndSize: Pair<Long, Int>) = transaction {
        val (page, size) = pageAndSize
        val meaningId = userId.let(Utils::uuidOrNull) ?: throw BadRequestException()

        ConversationDto
            .find{
                (Conversations.userId eq meaningId or
                        (Conversations.targetUserId eq meaningId)) }
            .limit(size, offset = page)
            .orderBy(Conversations.createdAt to SortOrder.ASC)
            .map(mapper::asResponse)
    }
    override fun deleteConversation(conversationId: String) = transaction {
        val mConversationId = conversationId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        ConversationDto.findById(mConversationId)
            ?.run {
                delete()
                id.value.toString()
            }
            ?: throw NotFoundException()
    }
}