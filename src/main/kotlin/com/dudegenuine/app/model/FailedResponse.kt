package com.dudegenuine.app.model

import kotlinx.serialization.Serializable

/**
 * Thu, 08 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class FailedResponse(
    val code: Int,
    val message: String
)
