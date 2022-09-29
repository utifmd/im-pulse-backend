package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.Users
import com.dudegenuine.app.entity.Verifications
import com.dudegenuine.app.entity.VerifierDto
import com.dudegenuine.app.mapper.contract.IVerifierMapper
import com.dudegenuine.app.model.verifier.VerifierRequest
import com.dudegenuine.app.repository.common.Utils
import com.dudegenuine.app.repository.contract.IVerifierRepository
import com.dudegenuine.app.repository.validation.AlreadyExistException
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Wed, 28 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class VerifierRepository(
    private val mapper: IVerifierMapper): IVerifierRepository {

    override fun create(request: VerifierRequest) = transaction {
        val mUserId = request.userId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val dto = VerifierDto.find { Verifications.payload eq request.payload }
        if (!dto.empty()) throw AlreadyExistException()
        VerifierDto.new {
            type = request.type
            payload = request.payload
            updatedAt = null
            userId = EntityID(mUserId, Users)
        }.let(mapper::asResponse)
    }

    override fun read(verifierId: String) = transaction {
        val mVerifierId = verifierId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val dto = VerifierDto.findById(mVerifierId) ?: throw NotFoundException()
        dto.let(mapper::asResponse)
    }

    override fun delete(verifierId: String) = transaction {
        val id = verifierId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        val dto = VerifierDto.findById(id) ?: throw NotFoundException()
        dto.delete()
        dto.id.value.toString()
    }

    override fun list(userId: String, pageAndSize: Pair<Long, Int>) = transaction {
        val mUserId = userId.let(Utils::uuidOrNull) ?: throw BadRequestException()
        VerifierDto
            .find { Verifications.userId eq mUserId }
            .limit(pageAndSize.second, pageAndSize.first)
            .map(mapper::asResponse)
    }
}