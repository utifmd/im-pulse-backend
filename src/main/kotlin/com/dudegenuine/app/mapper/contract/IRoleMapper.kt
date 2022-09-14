package com.dudegenuine.app.mapper.contract

import com.dudegenuine.app.entity.RoleDto
import com.dudegenuine.app.model.role.RoleResponse

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IRoleMapper {
    fun asResponse(dto: RoleDto): RoleResponse
}