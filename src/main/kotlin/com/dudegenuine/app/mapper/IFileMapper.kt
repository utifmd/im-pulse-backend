package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.FileDto
import com.dudegenuine.app.model.file.File
import io.ktor.http.content.*

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IFileMapper {
    suspend fun asDto(filePartData: MultiPartData): FileDto
    fun asFile(dto: FileDto): File
    fun asFileOrNull(dto: FileDto?): File?
}