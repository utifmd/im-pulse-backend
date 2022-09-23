package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.*
import com.dudegenuine.app.model.participant.ParticipantCreateRequest
import com.dudegenuine.app.repository.contract.IParticipantRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

/**
 * Fri, 16 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ParticipantRepository(database: Database): IParticipantRepository {
    init {
        transaction { SchemaUtils.create(Participants) }
    }
    override fun requireCreateParticipants(
        vararg requests: ParticipantCreateRequest) = transaction {
        val mCreatedAt = System.currentTimeMillis()
        requests.forEach { (mUserId, mConverseId, mRole) ->
            val participants = ParticipantDto.find {
                Participants.conversationId eq UUID.fromString(mConverseId)
            }
            if (!participants.empty()) return@transaction

            ParticipantDto.new {
                role = mRole
                isRead = false
                createdAt = mCreatedAt
                userDto = UserDto[UUID.fromString(mUserId)]
                conversationId = EntityID(UUID.fromString(mConverseId), Conversations)
            }
        }
    }
}