package com.dudegenuine.app.model

import kotlinx.serialization.Serializable

/**
 * Thu, 08 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class SuccessResponse<T>(
    val data: T,
)