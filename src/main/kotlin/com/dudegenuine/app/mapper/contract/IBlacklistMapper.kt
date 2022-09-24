package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.BlacklistDto
import com.dudegenuine.app.model.blacklist.BlacklistResponse

/**
 * Sat, 24 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IBlacklistMapper {
    fun asResponse(dto: BlacklistDto): BlacklistResponse
}