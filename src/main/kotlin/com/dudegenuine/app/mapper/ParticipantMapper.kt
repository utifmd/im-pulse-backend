package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.ParticipantDto
import com.dudegenuine.app.mapper.contract.IParticipantMapper
import com.dudegenuine.app.mapper.contract.IUserMapper
import com.dudegenuine.app.model.participant.ParticipantResponse

/**
 * Fri, 23 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ParticipantMapper(
    private val userMapper: IUserMapper): IParticipantMapper {

    override fun asResponse(dto: ParticipantDto) = with(dto) {
        ParticipantResponse(
            role = dto.role,
            isRead = dto.isRead,
            createdAt = dto.createdAt,
            user = dto.userDto?.let(userMapper::asUserResponse)
        )
    }
}