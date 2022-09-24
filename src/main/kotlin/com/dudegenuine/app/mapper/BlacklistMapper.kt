package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.BlacklistDto
import com.dudegenuine.app.mapper.contract.IBlacklistMapper
import com.dudegenuine.app.mapper.contract.IUserMapper
import com.dudegenuine.app.model.blacklist.BlacklistResponse

/**
 * Sat, 24 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class BlacklistMapper(
    private val userMapper: IUserMapper): IBlacklistMapper {

    override fun asResponse(dto: BlacklistDto) = BlacklistResponse(
        blacklistId = dto.id.value.toString(),
        targetUserId = dto.targetUserDto?.id?.value.toString(),
        targetUser = dto.targetUserDto?.let(userMapper::asUserHalfResponse),
        userId = dto.userId.value.toString(),
    )
}