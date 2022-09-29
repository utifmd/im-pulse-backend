package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.entity.VerifierDto
import com.dudegenuine.app.model.verifier.VerifierRequest
import com.dudegenuine.app.model.verifier.VerifierResponse

/**
 * Wed, 28 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IVerifierRepository {
    fun create(request: VerifierRequest): VerifierResponse
    fun read(verifierId: String): VerifierResponse
    fun delete(verifierId: String): String
    fun list(userId: String, pageAndSize: Pair<Long, Int>): List<VerifierResponse>
}