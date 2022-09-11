package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.ImageDto
import com.dudegenuine.app.mapper.contract.IImageMapper
import com.dudegenuine.app.model.image.ImageResponse

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ImageMapper: IImageMapper {
    override fun asResponse(dto: ImageDto) = ImageResponse(
        url = dto.url,
        updatedAt = dto.updatedAt
    )
}