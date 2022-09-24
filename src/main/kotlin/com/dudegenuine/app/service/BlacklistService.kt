package com.dudegenuine.app.service

import com.dudegenuine.app.model.blacklist.BlacklistRequest
import com.dudegenuine.app.repository.contract.IBlacklistRepository
import com.dudegenuine.app.repository.validation.InternalErrorException
import com.dudegenuine.app.service.contract.IBlacklistService

/**
 * Sat, 24 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class BlacklistService(
    private val repository: IBlacklistRepository): IBlacklistService {

    override fun addBlacklist(request: BlacklistRequest) =
        try{ repository.createBlacklist(request) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }

    override fun removeBlacklist(blacklistId: String) =
        try { repository.deleteBlacklist(blacklistId) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }

    override fun pagedBlacklists(userId: String, pageAndSize: Pair<Long, Int>) =
        try { repository.listBlacklist(userId, pageAndSize) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
}