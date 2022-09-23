package com.dudegenuine.app.service

import com.dudegenuine.app.model.device.DeviceRequest
import com.dudegenuine.app.repository.contract.IDeviceRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.InternalErrorException
import com.dudegenuine.app.service.contract.IDeviceService

/**
 * Mon, 12 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class DeviceService(
    private val repository: IDeviceRepository): IDeviceService {

    override fun addDevice(request: DeviceRequest) =
        try { repository.createDevice(request) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
    override fun removeDevice(content: String) =
        try { repository.deleteDevice(content) } catch (e: Exception){
            throw InternalErrorException(e.localizedMessage)
        }
}