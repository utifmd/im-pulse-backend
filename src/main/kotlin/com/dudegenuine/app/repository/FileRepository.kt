package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.Files
import com.dudegenuine.app.mapper.IFileMapper
import com.dudegenuine.app.repository.validation.NotFoundException
import io.ktor.http.content.*
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.entity.find
import org.ktorm.entity.map
import org.ktorm.entity.sequenceOf

/**
 * Wed, 07 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class FileRepository(
    private val database: Database,
    private val mapper: IFileMapper): IFileRepository {

    override fun getFile(id: String) = database
        .sequenceOf(Files)
        .find{ it.id eq id }
        ?. let(mapper::asFileOrNull)
        ?: throw NotFoundException()

    override fun removeFile(id: String) {
        database.delete(Files){ it.id eq id }
    }
    override suspend fun postFile(file: MultiPartData): String {
        val dto = mapper.asDto(file)
        database.useTransaction {
            database.insert(Files){
                set(Files.id, dto.id)
                set(Files.type, dto.type)
                set(Files.data, dto.data)
            }
        }
        return dto.id
    }
}