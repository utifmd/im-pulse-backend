package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.FileDto
import com.dudegenuine.app.entity.Files
import com.dudegenuine.app.mapper.contract.IFileMapper
import com.dudegenuine.app.repository.contract.IFileRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.NotFoundException
import io.ktor.http.content.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class FileRepository(
    private val mapper: IFileMapper, database: Database): IFileRepository {

    override fun getFile(id: String) = transaction {
        FileDto.find { Files.fileId eq id }
            .firstOrNull()
            ?.let(mapper::asFileOrNull)
            ?: throw NotFoundException()
    }

    override fun deleteFile(id: String) = transaction {
        val files = FileDto.find { Files.fileId eq id }
        val dto = files.firstOrNull() ?: throw NotFoundException()

        dto.run {
            delete()
            fileId
        }
    }
    override suspend fun postFile(file: MultiPartData): String {
        val generatedFileId = "FLE-${UUID.randomUUID()}"
        file.forEachPart { partData ->
            val requestType = partData.contentType?.contentType ?: throw BadRequestException()

            if(partData is PartData.FileItem) transaction {
                FileDto.new {
                    type = requestType
                    fileId = generatedFileId
                    data = partData.streamProvider().readBytes()
                }
            }
        }
        return generatedFileId
    }
}