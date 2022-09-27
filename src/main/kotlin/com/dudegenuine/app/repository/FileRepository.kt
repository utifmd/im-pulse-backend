package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.FileDto
import com.dudegenuine.app.entity.Files
import com.dudegenuine.app.mapper.contract.IFileMapper
import com.dudegenuine.app.model.file.FileResponse
import com.dudegenuine.app.repository.contract.IFileRepository
import com.dudegenuine.app.repository.contract.IFileRepository.Companion.ORIGINAL
import com.dudegenuine.app.repository.contract.IFileRepository.Companion.THUMBNAIL
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.InternalErrorException
import com.dudegenuine.app.repository.validation.NotFoundException
import io.ktor.http.content.*
import net.coobird.thumbnailator.Thumbnails
import net.coobird.thumbnailator.filters.Canvas
import net.coobird.thumbnailator.geometry.Position
import net.coobird.thumbnailator.geometry.Positions
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.awt.Color
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class FileRepository(
    private val mapper: IFileMapper): IFileRepository {
    init {
        transaction { SchemaUtils.create(Files) }
    }
    override suspend fun createImageAndThumbnailFile(partData: PartData.FileItem) = transaction {
        val responses = mutableListOf<FileResponse>()
        val contentType = partData.contentType?.contentType ?: throw BadRequestException("filetype")
        val outStream = ByteArrayOutputStream()

        partData.streamProvider().transferTo(outStream)

        val original = ByteArrayInputStream(outStream.toByteArray()).readBytes()
        val thumbnailOutStream = ByteArrayOutputStream()

        Thumbnails
            .of(ByteArrayInputStream(outStream.toByteArray()))
            .size(200, 200)
            .crop(Positions.CENTER)
            .outputQuality(0.5f)
            .toOutputStream(thumbnailOutStream)

        val thumbnail = thumbnailOutStream.toByteArray()
        setOf(thumbnail, original).forEachIndexed { idx, mData ->
            val dto = FileDto.new {
                type = contentType
                data = mData
            }
            if (idx != 0) responses.add(dto.let(mapper::asResponse))
            else responses.add(dto.let(mapper::asResponse).copy(role = THUMBNAIL))
        }
        partData.dispose()
        responses
    }
    override fun readFile(id: String) = transaction {
        val dto = FileDto.findById(UUID.fromString(id)) ?: throw NotFoundException()

        dto.let(mapper::asFile)
    }
    override fun deleteFile(id: String) = transaction {
        val files = FileDto.findById(UUID.fromString(id))
        val dto = files ?: throw NotFoundException()

        dto.run {
            delete()
            this.id.value.toString()
        }
    }
}