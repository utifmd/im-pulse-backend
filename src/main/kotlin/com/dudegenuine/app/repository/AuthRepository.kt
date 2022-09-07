package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.Auths
import com.dudegenuine.app.model.auth.AuthCreateRequest
import com.dudegenuine.app.model.auth.AuthUpdateRequest
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update

/**
 * Tue, 06 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class AuthRepository(
    private val database: Database): IAuthRepository {

    override fun postAuth(createRequest: AuthCreateRequest) = with(createRequest){
        database.insert(Auths){ auth ->
            set(auth.email, email)
            set(auth.username, username)
            set(auth.password, password)
            set(auth.lastPassword, lastPassword)
            set(auth.updatedAt, null)
        }; Unit
    }
    override fun putAuth(updateRequest: AuthUpdateRequest) = with(updateRequest){
        database.update(Auths){ auth ->
            set(auth.email, email)
            set(auth.username, username)
            set(auth.password, password)
            set(auth.lastPassword, lastPassword)
            set(auth.updatedAt, System.currentTimeMillis())
            where { auth.id eq currentAuthId }
        }; Unit
    }
}