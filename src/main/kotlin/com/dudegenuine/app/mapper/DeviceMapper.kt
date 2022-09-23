package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.DeviceDto
import com.dudegenuine.app.mapper.contract.IDeviceMapper
import com.dudegenuine.app.model.device.DeviceResponse

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class DeviceMapper: IDeviceMapper {
    override fun asResponse(dto: DeviceDto) = DeviceResponse(
        token = dto.token,
        type = dto.type,
        updatedAt = dto.updatedAt
    )
}