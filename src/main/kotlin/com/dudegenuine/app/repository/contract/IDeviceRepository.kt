package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.device.DeviceRequest
import com.dudegenuine.app.model.device.DeviceResponse

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IDeviceRepository {
    fun createDevice(request: DeviceRequest): DeviceResponse
    fun deleteDevice(content: String): String
}