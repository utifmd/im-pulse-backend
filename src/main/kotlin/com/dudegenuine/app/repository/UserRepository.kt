package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.Tokens
import com.dudegenuine.app.entity.Users
import com.dudegenuine.app.entity.Verifications
import com.dudegenuine.app.mapper.IUserMapper
import com.dudegenuine.app.model.User
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.*

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class UserRepository(
    private val database: Database,
    private val mapper: IUserMapper): IUserRepository {
    override fun getAllUsers() = database
        .sequenceOf(Users)
        .map(mapper::asUser)
        .map(::oneToManyCopy)
    override fun getUser(userId: String): User? = database
        .sequenceOf(Users)
        .find{ it.id eq userId }
        .let(mapper::asUserOrNull)
    private fun oneToManyCopy(user: User) = with(user){
        copy(verifier = listVerifier(id), tokens = listTokens(id))
    }
    private fun listVerifier(userId: String) = database
        .sequenceOf(Verifications)
        .filter{ it.userId eq userId }
        .map(mapper::asVerifier)
    private fun listTokens(userId: String) = database
        .sequenceOf(Tokens)
        .filter { it.ownerId eq userId }
        .map(mapper::asToken)
}