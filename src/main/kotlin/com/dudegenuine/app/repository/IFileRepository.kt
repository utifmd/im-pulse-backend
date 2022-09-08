package com.dudegenuine.app.repository

import com.dudegenuine.app.model.file.File
import io.ktor.http.content.*

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IFileRepository {
    fun getFile(id: String): File
    fun removeFile(id: String)
    suspend fun postFile(file: MultiPartData): String
}