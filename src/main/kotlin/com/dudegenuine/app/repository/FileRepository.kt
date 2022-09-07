package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.Files
import com.dudegenuine.app.mapper.IFileMapper
import com.dudegenuine.app.model.file.File
import io.ktor.http.content.*
import org.ktorm.database.Database
import org.ktorm.dsl.insert

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class FileRepository(
    private val database: Database,
    private val mapper: IFileMapper): IFileRepository {

    override fun getFile(id: String): File {
        TODO("Not yet implemented")
    }

    override suspend fun postFile(file: MultiPartData): String {
        val dto = mapper.asDto(file)
        database.insert(Files){
            set(Files.id, dto.id)
            set(Files.type, dto.type)
            set(Files.data, dto.data)
        }
        return dto.id
    }
}