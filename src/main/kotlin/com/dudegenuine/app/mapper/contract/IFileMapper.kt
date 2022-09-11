package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.FileDto
import com.dudegenuine.app.model.file.File
import io.ktor.http.content.*

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IFileMapper {
    fun asFile(dto: FileDto): File
    fun asFileOrNull(dto: FileDto?): File?
}