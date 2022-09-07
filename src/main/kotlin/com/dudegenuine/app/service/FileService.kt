package com.dudegenuine.app.service

import com.dudegenuine.app.repository.IFileRepository
import io.ktor.http.content.*

/**
 * Thu, 08 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class FileService(
    private val repository: IFileRepository): IFileService {
    override fun readFile(id: String) = repository.getFile(id)

    override suspend fun uploadFile(
        filePartData: MultiPartData) = repository.postFile(filePartData)
}