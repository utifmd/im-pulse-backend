package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.participant.ParticipantCreateRequest

/**
 * Fri, 16 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IParticipantRepository {
    fun requireCreateParticipant(request: ParticipantCreateRequest)
}