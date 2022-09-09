package com.dudegenuine.app.repository

import com.dudegenuine.app.entities.AuthDto
import com.dudegenuine.app.entities.Auths
import com.dudegenuine.app.mapper.IAuthMapper
import com.dudegenuine.app.model.auth.AuthCreateRequest
import com.dudegenuine.app.model.auth.AuthResponse
import com.dudegenuine.app.model.auth.AuthUpdateRequest
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.IllegalStateException

/**
 * Tue, 06 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class AuthRepository(
    private val database: Database,
    private val mapper: IAuthMapper): IAuthRepository {
    override fun getAuths(pageAndSize: Pair<Long, Int>) = transaction {
        val (page, size) = pageAndSize

        AuthDto
            .all()
            .limit(size, offset = page)
            .map(mapper::asResponse)
    }

    override fun postAuth(createRequest: AuthCreateRequest) = with(createRequest){
        /*database.insert(Auths){ auth ->
            set(auth.email, email)
            set(auth.username, username)
            set(auth.password, password)
            set(auth.lastPassword, lastPassword)
            set(auth.updatedAt, null)
        }; Unit*/
    }
    override fun putAuth(updateRequest: AuthUpdateRequest) = with(updateRequest){
        /*database.update(Auths){ auth ->
            set(auth.email, email)
            set(auth.username, username)
            set(auth.password, password)
            set(auth.lastPassword, lastPassword)
            set(auth.updatedAt, System.currentTimeMillis())
            where { auth.id eq currentAuthId }
        }; Unit*/
    }
}