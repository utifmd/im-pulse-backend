package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.ParticipantDto
import com.dudegenuine.app.model.participant.ParticipantResponse

/**
 * Fri, 23 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IParticipantMapper {
    fun asResponse(dto: ParticipantDto): ParticipantResponse
}