package com.dudegenuine.app.service

import com.dudegenuine.app.model.role.RoleCreateRequest
import com.dudegenuine.app.repository.contract.IRoleRepository
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.IRoleService

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class RoleService(
    private val repository: IRoleRepository
): IRoleService {

    override fun addRole(request: RoleCreateRequest) =
        try { repository.createRole(request) } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
    override fun findRole(roleId: String) =
        try { repository.readRole(roleId) } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
}