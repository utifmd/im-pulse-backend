package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.level.LevelCreateRequest
import com.dudegenuine.app.model.level.LevelResponse

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface ILevelService {
    fun addLevel(request: LevelCreateRequest): LevelResponse
    fun findLevel(levelId: String): LevelResponse
}