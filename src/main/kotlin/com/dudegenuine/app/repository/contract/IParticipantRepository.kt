package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.participant.ParticipantCreateRequest

/**
 * Fri, 16 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IParticipantRepository {
    fun requireCreateParticipants(vararg requests: ParticipantCreateRequest)
    companion object {
        const val SENDER = "SENDER"
        const val RECIPIENT = "RECIPIENT"
    }
}