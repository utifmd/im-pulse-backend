package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.file.File
import com.dudegenuine.app.model.file.FileResponse
import io.ktor.http.content.*
import java.io.InputStream

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IFileRepository {
    companion object{
        const val THUMBNAIL = "THUMBNAIL"
        const val ORIGINAL = "ORIGINAL"
    }
    fun readFile(id: String): File
    fun deleteFile(id: String): String
    suspend fun createImageFile(multiPartData: MultiPartData, compress: Boolean = false): FileResponse
    /*suspend fun createImageAndThumbnailFile(contentType: String, inputStream: InputStream): List<FileResponse>*/
}