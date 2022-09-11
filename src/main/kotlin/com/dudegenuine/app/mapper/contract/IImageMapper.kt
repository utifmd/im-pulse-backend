package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.ImageDto
import com.dudegenuine.app.model.image.ImageResponse

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IImageMapper {
    fun asResponse(dto: ImageDto): ImageResponse
}