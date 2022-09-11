package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.image.ImageCreateRequest
import com.dudegenuine.app.model.image.ImageResponse

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IImageRepository {
    fun createImage(request: ImageCreateRequest): ImageResponse
}