package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.ImageDto
import com.dudegenuine.app.entity.Images
import com.dudegenuine.app.entity.Users
import com.dudegenuine.app.mapper.contract.IImageMapper
import com.dudegenuine.app.model.image.ImageCreateRequest
import com.dudegenuine.app.repository.contract.IImageRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class ImageRepository(
    private val mapper: IImageMapper): IImageRepository {
    init {
        transaction { SchemaUtils.create(Images) }
    }
    override fun createImage(request: ImageCreateRequest) = transaction {
        val dto = ImageDto.new {
            url = request.url
            role = request.role
            profileId = EntityID(UUID.fromString(request.profileId), Users) //ProfileDto[UUID.fromString(request.profileId)]
            updatedAt = null
        }
        dto.let (mapper::asResponse)
    }
}