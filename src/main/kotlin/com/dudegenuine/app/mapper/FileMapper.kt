package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.FileDto
import com.dudegenuine.app.mapper.contract.IFileMapper
import com.dudegenuine.app.model.file.File

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class FileMapper: IFileMapper {
    override fun asFile(dto: FileDto) = with(dto){
        File(type, data)
    }
    override fun asFileOrNull(dto: FileDto?) = dto?.let(::asFile)
}