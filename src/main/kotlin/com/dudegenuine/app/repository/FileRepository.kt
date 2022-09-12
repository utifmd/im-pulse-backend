package com.dudegenuine.app.repository

import ch.qos.logback.core.CoreConstants.EMPTY_STRING
import com.dudegenuine.app.entity.FileDto
import com.dudegenuine.app.entity.Files
import com.dudegenuine.app.mapper.contract.IFileMapper
import com.dudegenuine.app.repository.contract.IFileRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.NotFoundException
import io.ktor.http.content.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class FileRepository(
    private val mapper: IFileMapper, database: Database): IFileRepository {
    init {
        transaction { SchemaUtils.create(Files) }
    }
    override fun getFile(id: String) = transaction {
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
    override suspend fun postFile(file: MultiPartData): String {
        var generatedFileId: String? = null
        file.forEachPart { partData ->
            val requestType = partData.contentType?.contentType ?: throw BadRequestException()

            if(partData is PartData.FileItem) transaction {
                val dto = FileDto.new {
                    type = requestType
                    data = partData.streamProvider().readBytes()
                }
                generatedFileId = dto.id.value.toString()
            }
        }
        return generatedFileId ?: file.readPart()?.toString() ?: EMPTY_STRING
    }
}