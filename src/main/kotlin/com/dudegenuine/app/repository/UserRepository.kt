package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.*
import com.dudegenuine.app.mapper.IUserMapper
import com.dudegenuine.app.model.token.TokenResponse
import com.dudegenuine.app.model.user.*
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.filter
import org.ktorm.entity.find
import org.ktorm.entity.map
import org.ktorm.entity.sequenceOf

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class UserRepository(
    private val database: Database,
    private val mapper: IUserMapper): IUserRepository {
    override fun getAllUsersCensor() = database
        .sequenceOf(Users)
        .map(mapper::asUserCensorResponse)
        .map(::oneToManyAsCensor)

    /*override fun getAllUsersComplete() = database
        .sequenceOf(Users)
        .map(mapper::asUserComplete)
        .map(::oneToManyAsComplete)*/

    override fun getUsersCensor(pageAndSize: Pair<Int, Int>): List<UserCensorResponse> {
        val (page, size) = pageAndSize

        return database
            .from(Users)
            .innerJoin(Auths, on = Users.authId eq Auths.id)
            .innerJoin(Profiles, on = Users.profileId eq Profiles.id)
            .innerJoin(Levels, on = Users.levelId eq Levels.id)
            .innerJoin(Images, on = Profiles.imageId eq Images.id)
            .select()
            .limit(page, size)
            .map(mapper::asDto)
            .map(mapper::asUserCensorResponse)
            .map(::oneToManyAsCensor)
    }
    /*override fun getUsersComplete(pageAndSize: Pair<Int, Int>): List<User.Complete> {
        val (page, size) = pageAndSize

        return database
            .from(Users)
            .innerJoin(Auths, on = Users.authId eq Auths.id)
            .innerJoin(Profiles, on = Users.profileId eq Profiles.id)
            .innerJoin(Levels, on = Users.levelId eq Levels.id)
            .innerJoin(Images, on = Profiles.imageId eq Images.id)
            .select()
            .limit(page, size)
            .map(mapper::asDto)
            .map(mapper::asUserComplete)
            .map(::oneToManyAsComplete)
    }*/
    override fun getUserCensor(userId: String) = database
        .sequenceOf(Users)
        .find{ it.id eq userId }
        .let(mapper::asUserCensorResponseOrNull)

    override fun getUserComplete(userId: String) = database
        .sequenceOf(Users)
        .find{ it.id eq userId }
        .let(mapper::asUserCompleteResponseOrNull)

    override fun postUser(userRequest: UserCreateRequest) = with(userRequest){
        database.insert(Users){user ->
            set(user.id, id)
            set(user.firstName, firstName)
            set(user.lastName, lastName)
            set(user.authId, authId)
            set(user.profileId, profileId)
            set(user.levelId, levelId)
            set(user.createdAt, System.currentTimeMillis())
            set(user.updatedAt, null)
        }; Unit //mapper.asUserCompleteResponse(userRequest)
    }

    override fun putUser(userRequest: UserUpdateRequest) = with(userRequest){
        database.update(Users){user ->
            set(user.firstName, firstName)
            set(user.lastName, lastName)
            set(user.createdAt, createdAt)
            set(user.updatedAt, System.currentTimeMillis())
            where{ user.id eq currentUserId }
        }; Unit
    }

    private fun oneToManyAsCensor(user: UserCensorResponse) = with(user){
        copy(tokens = listTokensContent(id))
    }
    /*private fun oneToManyAsComplete(user: User.Complete) = with(user){
        copy(verifier = listVerifier(id), tokens = listTokens(id))
    }*/
    private fun listVerifier(userId: String) = database
        .sequenceOf(Verifications)
        .filter{ it.userId eq userId }
        .map(mapper::asVerifier)

    private fun listTokens(userId: String) = database
        .sequenceOf(Tokens)
        .filter{ it.ownerId eq userId }
        .map(mapper::asToken)

    private fun listTokensContent(userId: String) =
        listTokens(userId)
            .map(TokenResponse::content)
}