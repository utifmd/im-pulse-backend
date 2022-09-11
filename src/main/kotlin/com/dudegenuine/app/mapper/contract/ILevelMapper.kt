package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.LevelDto
import com.dudegenuine.app.model.level.LevelResponse

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface ILevelMapper {
    fun asResponse(dto: LevelDto): LevelResponse
}