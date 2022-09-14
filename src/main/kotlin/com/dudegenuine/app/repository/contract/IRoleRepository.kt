package com.dudegenuine.app.repository.contract

import com.dudegenuine.app.model.role.RoleCreateRequest
import com.dudegenuine.app.model.role.RoleResponse

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IRoleRepository {
    fun createRole(request: RoleCreateRequest): RoleResponse
    fun readRole(roleId: String): RoleResponse
}