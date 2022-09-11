package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.ImageDto
import com.dudegenuine.app.entity.Images
import com.dudegenuine.app.entity.ProfileDto
import com.dudegenuine.app.mapper.contract.IImageMapper
import com.dudegenuine.app.model.image.ImageCreateRequest
import com.dudegenuine.app.model.image.ImageResponse
import com.dudegenuine.app.repository.contract.IImageRepository
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ImageRepository(
    private val mapper: IImageMapper, database: Database): IImageRepository {
    init {
        transaction { SchemaUtils.create(Images) }
    }
    override fun createImage(request: ImageCreateRequest) = transaction {
        val dto = ImageDto.new {
            url = request.url
            profileDto = ProfileDto[UUID.fromString(request.profileId)]
        }
        dto.let (mapper::asResponse)
    }
}