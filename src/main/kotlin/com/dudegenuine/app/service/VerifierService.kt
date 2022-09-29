package com.dudegenuine.app.service

import com.dudegenuine.app.model.verifier.VerifierRequest
import com.dudegenuine.app.repository.contract.IVerifierRepository
import com.dudegenuine.app.repository.validation.InternalErrorException
import com.dudegenuine.app.service.contract.IVerifierService

/**
 * Wed, 28 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class VerifierService(
    private val repository: IVerifierRepository): IVerifierService {

    override fun add(request: VerifierRequest) = try { repository.create(request) } catch (e: Exception){
        throw InternalErrorException(e.localizedMessage)
    }

    override fun find(verifierId: String) = try { repository.read(verifierId) } catch (e: Exception){
        throw InternalErrorException(e.localizedMessage)
    }

    override fun remove(verifierId: String) = try { repository.delete(verifierId) } catch (e: Exception){
        throw InternalErrorException(e.localizedMessage)
    }

    override fun paged(userId: String, pageAndSize: Pair<Long, Int>) = try { repository.list(userId, pageAndSize) } catch (e: Exception){
        throw InternalErrorException(e.localizedMessage)
    }
}