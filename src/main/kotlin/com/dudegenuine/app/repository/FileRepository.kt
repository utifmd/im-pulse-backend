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
import kotlinx.coroutines.Dispatchers
import net.coobird.thumbnailator.Thumbnails
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.ByteArrayOutputStream
import java.io.InputStream
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
    override suspend fun createImageFile(
        multiPartData: MultiPartData, compress: Boolean): FileResponse {

        var file: FileResponse? = null
        multiPartData.forEachPart { partData ->
            val mType = partData.contentType?.contentType ?: throw BadRequestException("contentType")
            val mOutStream = ByteArrayOutputStream()

            if (partData is PartData.FileItem){
                if (compress) {
                    Thumbnails.of(partData.streamProvider())
                        .size(200, 200)
                        .outputQuality(0.5f)
                        .toOutputStream(mOutStream)
                }
                val dto = transaction {
                    FileDto.new {
                        type = mType
                        data = if (compress)
                            mOutStream.toByteArray() else
                            partData.streamProvider().readBytes()
                    }
                }
                file = FileResponse(
                    id = dto.id.value.toString(),
                    type = mType,
                    role = if(compress) THUMBNAIL else ORIGINAL
                )
            }
        }
        return file ?: throw InternalErrorException()
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

    /*override suspend fun createImageAndThumbnailFile(
        contentType: String,
        inputStream: InputStream
    ) = newSuspendedTransaction {
        val files = mutableListOf<FileResponse>()
        val mOutStream = ByteArrayOutputStream()

        Thumbnails
            .of(inputStream)
            .size(200, 200)
            .outputQuality(0.5f)
            .toOutputStream(mOutStream)

        suspendedTransaction(Dispatchers.Default) {
            val thumbnail = FileDto.new {
                type = contentType
                data = mOutStream.toByteArray()
            }
            files.add(FileResponse(thumbnail.id.value.toString(), contentType, THUMBNAIL))
        }
        suspendedTransaction(Dispatchers.Main) {
            val original = FileDto.new {
                type = contentType
                data = inputStream.readBytes()
            }
            files.add(FileResponse(original.id.value.toString(), contentType, ORIGINAL))
        }
        files
    }*/
}