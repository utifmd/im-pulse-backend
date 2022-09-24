package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.blacklist.BlacklistRequest
import com.dudegenuine.app.model.blacklist.BlacklistResponse

/**
 * Fri, 23 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IBlacklistRepository {
    fun createBlacklist(request: BlacklistRequest): BlacklistResponse
    fun deleteBlacklist(blacklistId: String): String
    fun listBlacklist(userId: String, pageAndSize: Pair<Long, Int>): List<BlacklistResponse>
    fun isAccessBlocked(senderAndRecipient: Pair<String, String>): Boolean
}