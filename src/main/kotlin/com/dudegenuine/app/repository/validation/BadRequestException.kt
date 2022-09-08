package com.dudegenuine.app.repository.validation

/**
 * Thu, 08 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class BadRequestException(param: String? = null): Throwable(
    param?.let{ "Bad request parameter of $it" } ?: "Bad request."
)