package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.*
import com.dudegenuine.app.model.participant.ParticipantCreateRequest
import com.dudegenuine.app.repository.common.Utils
import com.dudegenuine.app.repository.contract.IParticipantRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

/**
 * Fri, 16 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ParticipantRepository: IParticipantRepository {
    init {
        transaction { SchemaUtils.create(Participants) }
    }
    override fun requireCreateParticipants(vararg requests: ParticipantCreateRequest) /*= transaction*/ {
        val mCreatedAt = System.currentTimeMillis()
        requests.forEach { (mUserId, mConverseId, mRole) ->
            val converseId = mConverseId.let(Utils::uuidOrNull) ?: throw BadRequestException()
            val userId = mUserId.let(Utils::uuidOrNull) ?: throw BadRequestException()
            val participants = ParticipantDto.find {
                Participants.conversationId eq converseId
            }
            if (!participants.empty()) return/*@transaction*/
            ParticipantDto.new {
                role = mRole
                isRead = false
                createdAt = mCreatedAt
                userDto = UserDto[userId]
                conversationId = EntityID(converseId, Conversations)
            }
        }
    }
}