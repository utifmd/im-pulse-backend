package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.LevelDto
import com.dudegenuine.app.mapper.contract.ILevelMapper
import com.dudegenuine.app.model.level.LevelResponse

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class LevelMapper: ILevelMapper {
    override fun asResponse(dto: LevelDto) = with(dto) {
        LevelResponse(
            status = status
        )
    }
}