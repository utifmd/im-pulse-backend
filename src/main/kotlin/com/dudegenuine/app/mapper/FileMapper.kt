package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.FileDto
import com.dudegenuine.app.model.file.File
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
                ?: ContentType.MultiPart.toString()

            when(partData){
                is PartData.FileItem -> {
                    id = "FILE-${UUID.randomUUID()}"
                    data = partData.streamProvider().readBytes()
                }
                else -> Unit
            }

        }
    }
    override fun asFile(dto: FileDto) = with(dto){
        File(id, type, data)
    }
}