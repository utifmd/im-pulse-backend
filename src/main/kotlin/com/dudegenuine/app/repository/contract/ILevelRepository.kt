package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.level.LevelCreateRequest
import com.dudegenuine.app.model.level.LevelResponse

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface ILevelRepository {
    fun createLevel(request: LevelCreateRequest): LevelResponse
    fun readLevel(levelId: String): LevelResponse
}