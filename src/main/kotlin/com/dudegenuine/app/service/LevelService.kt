package com.dudegenuine.app.service

import com.dudegenuine.app.model.level.LevelCreateRequest
import com.dudegenuine.app.repository.contract.ILevelRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.ILevelService

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class LevelService(
    private val repository: ILevelRepository
): ILevelService {

    override fun addLevel(request: LevelCreateRequest) = try {
        repository.createLevel(request)
    } catch (e: Exception){
        throw BadRequestException(e.localizedMessage)
    }
    override fun findLevel(levelId: String) = try {
        repository.readLevel(levelId)
    } catch (e: Exception){
        throw BadRequestException(e.localizedMessage)
    }
}