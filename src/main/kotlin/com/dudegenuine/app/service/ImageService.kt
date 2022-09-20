package com.dudegenuine.app.service

import com.dudegenuine.app.model.image.ImageCreateRequest
import com.dudegenuine.app.model.image.ImageResponse
import com.dudegenuine.app.repository.contract.IImageRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.IImageService

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ImageService(
    private val repository: IImageRepository): IImageService {

    override fun addImage(request: ImageCreateRequest) =
        try { request.let(repository::createImage) } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
}