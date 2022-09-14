package com.dudegenuine.app.service.contract

import com.dudegenuine.app.model.role.RoleCreateRequest
import com.dudegenuine.app.model.role.RoleResponse

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IRoleService {
    fun addRole(request: RoleCreateRequest): RoleResponse
    fun findRole(roleId: String): RoleResponse
}