package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.ConversationDto
import com.dudegenuine.app.entity.ParticipantDto
import com.dudegenuine.app.entity.Participants
import com.dudegenuine.app.model.participant.ParticipantCreateRequest
import com.dudegenuine.app.repository.contract.IParticipantRepository
import com.dudegenuine.app.repository.validation.AlreadyExistException
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
    override fun requireCreateParticipant(request: ParticipantCreateRequest) {
        val (mUserId, mConverseId) = request
        val participants = ParticipantDto.find {
            Participants.conversationId eq UUID.fromString(mConverseId)
        }
        if (!participants.empty()) throw AlreadyExistException()

        ParticipantDto.new {
            type = "COUPLE"
            createdAt = System.currentTimeMillis()
            updatedAt = null
            userId = UUID.fromString(mUserId)
            conversationDto = ConversationDto[UUID.fromString(mConverseId)]
        }
    }
}