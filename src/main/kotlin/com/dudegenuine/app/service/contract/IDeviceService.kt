package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.device.DeviceRequest
import com.dudegenuine.app.model.device.DeviceResponse

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IDeviceService {
    fun addDevice(request: DeviceRequest): DeviceResponse
    fun removeDevice(content: String): String
}