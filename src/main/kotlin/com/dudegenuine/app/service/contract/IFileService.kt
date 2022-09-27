package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.file.File
import com.dudegenuine.app.model.file.FileResponse
import io.ktor.http.content.*

/**
 * Thu, 08 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IFileService {
    fun readFile(id: String): File
    fun deleteFile(id: String): String
    //suspend fun uploadFileImage(filePartData: MultiPartData, compress: Boolean = false): FileResponse
    suspend fun uploadImageFile(filePartData: MultiPartData): List<FileResponse> /*suspend fun uploadFile(filePartData: MultiPartData, compress: Boolean): FileResponse*/
}