package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.DeviceDto
import com.dudegenuine.app.model.device.DeviceResponse

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IDeviceMapper {
    fun asResponse(dto: DeviceDto): DeviceResponse
}