package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.blacklist.BlacklistRequest
import com.dudegenuine.app.model.blacklist.BlacklistResponse

/**
 * Sat, 24 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IBlacklistService {
    fun addBlacklist(request: BlacklistRequest): BlacklistResponse
    fun removeBlacklist(blacklistId: String): String
    fun pagedBlacklists(userId: String, pageAndSize: Pair<Long, Int>): List<BlacklistResponse> //fun isAccessBlocked(senderAndRecipient: Pair<String, String>): Boolean
}