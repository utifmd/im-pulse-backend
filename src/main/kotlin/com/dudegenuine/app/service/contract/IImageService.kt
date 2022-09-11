package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.image.ImageCreateRequest
import com.dudegenuine.app.model.image.ImageResponse

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IImageService {
    fun addImage(request: ImageCreateRequest): ImageResponse
}