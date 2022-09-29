package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.VerifierDto
import com.dudegenuine.app.model.verifier.VerifierResponse

/**
 * Wed, 28 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IVerifierMapper {
    fun asResponse(dto: VerifierDto): VerifierResponse
}