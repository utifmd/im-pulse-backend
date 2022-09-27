package com.dudegenuine.app.service

import com.dudegenuine.app.model.file.FileResponse
import com.dudegenuine.app.repository.contract.IFileRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.InternalErrorException
import com.dudegenuine.app.service.contract.IFileService
import io.ktor.http.content.*

/**
 * Thu, 08 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class FileService(
    private val repository: IFileRepository): IFileService {
    override fun readFile(id: String) = try { repository.readFile(id) } catch (e: Exception){
        throw InternalErrorException(e.localizedMessage)
    }
    override fun deleteFile(id: String) = try { id.let(repository::deleteFile) } catch (e: Exception){
        throw InternalErrorException(e.localizedMessage)
    }
    override suspend fun uploadImageFile(filePartData: MultiPartData) = try{
        filePartData.readPart()
            ?. let{ if (it is PartData.FileItem) repository.createImageAndThumbnailFile(it) else emptyList() }
            ?: throw BadRequestException()
    } catch (e: Exception){
        throw InternalErrorException(e.localizedMessage)
    }
}