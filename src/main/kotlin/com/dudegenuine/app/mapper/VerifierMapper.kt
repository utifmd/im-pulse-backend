package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.VerifierDto
import com.dudegenuine.app.mapper.contract.IVerifierMapper
import com.dudegenuine.app.model.verifier.VerifierResponse

/**
 * Wed, 28 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class VerifierMapper: IVerifierMapper {
    override fun asResponse(dto: VerifierDto) = VerifierResponse(
        type = dto.type,
        payload = dto.payload,
        updatedAt = dto.updatedAt
    )
}