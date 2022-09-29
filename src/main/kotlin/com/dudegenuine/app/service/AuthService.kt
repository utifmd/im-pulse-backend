package com.dudegenuine.app.service

import com.dudegenuine.app.model.auth.AuthRegisterRequest
import com.dudegenuine.app.model.auth.AuthLoginRequest
import com.dudegenuine.app.model.auth.AuthUpdateRequest
import com.dudegenuine.app.repository.common.Utils
import com.dudegenuine.app.repository.contract.IAuthRepository
import com.dudegenuine.app.repository.contract.IContactRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.InternalErrorException
import com.dudegenuine.app.service.contract.IAuthService
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class AuthService(
    private val authRepository: IAuthRepository,
    private val contactRepository: IContactRepository): IAuthService {

    override fun listAuths(pageAndSize: Pair<Long, Int>) =
        try { authRepository.readAuths(pageAndSize) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    override fun findAuth(authId: String) =
        try { authRepository.readAuth(authId) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    override fun onSignIn(request: AuthLoginRequest) = transaction {
        try {
            val mEmail = request.payload.let(contactRepository::readEmail)
            authRepository.onSignIn(request.copy(payload = mEmail))
        } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    }
    override fun onSignUp(request: AuthRegisterRequest) = try {
        if (!request.email.let(Utils::isEmailValid)) throw BadRequestException("email")

        authRepository.onSignUp(request)
    } catch (e: Exception){
        throw InternalErrorException(e.localizedMessage)
    }
    override fun deleteAuth(authId: String) =
        try { authRepository.deleteAuth(authId) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    override fun updateAuth(request: AuthUpdateRequest) =
        try { authRepository.updateAuth(request) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
}