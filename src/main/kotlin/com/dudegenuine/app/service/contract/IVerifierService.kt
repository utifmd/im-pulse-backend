package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.verifier.VerifierRequest
import com.dudegenuine.app.model.verifier.VerifierResponse

/**
 * Wed, 28 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IVerifierService {
    fun add(request: VerifierRequest): VerifierResponse
    fun find(verifierId: String): VerifierResponse
    fun remove(verifierId: String): String
    fun paged(userId: String, pageAndSize: Pair<Long, Int>): List<VerifierResponse>
}