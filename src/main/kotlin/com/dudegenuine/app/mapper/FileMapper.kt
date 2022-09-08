package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.FileDto
import com.dudegenuine.app.model.file.File
import com.dudegenuine.app.repository.validation.BadRequestException
import io.ktor.http.*
import io.ktor.http.content.*
import java.util.UUID

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class FileMapper: IFileMapper {
    override suspend fun asDto(filePartData: MultiPartData): FileDto = FileDto().apply {
        filePartData.forEachPart { partData ->
            type = partData.contentType
                ?. contentType
                ?: throw BadRequestException()

            when(partData){
                is PartData.FileItem -> {
                    id = "FLE-${UUID.randomUUID()}"
                    data = partData.streamProvider().readBytes()
                }
                else -> Unit
            }

        }
    }
    override fun asFile(dto: FileDto) = with(dto){
        File(id, type, data)
    }

    override fun asFileOrNull(dto: FileDto?) = dto?.let(::asFile)
}