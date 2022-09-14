package com.dudegenuine.app.repository

import com.dudegenuine.app.entity.DeviceDto
import com.dudegenuine.app.entity.Devices
import com.dudegenuine.app.entity.UserDto
import com.dudegenuine.app.mapper.contract.IDeviceMapper
import com.dudegenuine.app.model.device.DeviceRequest
import com.dudegenuine.app.repository.contract.IDeviceRepository
import com.dudegenuine.app.repository.validation.NotFoundException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class DeviceRepository(
    private val mapper: IDeviceMapper, database: Database): IDeviceRepository {
    init {
        transaction { SchemaUtils.create(Devices) }
    }
    override fun createDevice(request: DeviceRequest) = transaction{
        val (mContent, mType, mUserId) = request
        val dto = DeviceDto.new {
            token = mContent
            type = mType
            userDto = UserDto[UUID.fromString(mUserId)]
            updatedAt = null
        }
        dto.let(mapper::asResponse)
    }
    override fun deleteDevice(content: String) = transaction {
        val devices = DeviceDto.find{ Devices.token eq content }
        val dto = devices.firstOrNull() ?: throw NotFoundException()

        dto.delete()
        content
    }
}