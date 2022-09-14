package com.dudegenuine.app.mapper

import com.dudegenuine.app.entity.RoleDto
import com.dudegenuine.app.mapper.contract.IRoleMapper
import com.dudegenuine.app.model.role.RoleResponse

/**
 * Sun, 11 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class RoleMapper: IRoleMapper {
    override fun asResponse(dto: RoleDto) = with(dto) {
        RoleResponse(
            status = current
        )
    }
}