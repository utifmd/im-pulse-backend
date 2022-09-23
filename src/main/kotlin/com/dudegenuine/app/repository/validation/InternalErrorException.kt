package com.dudegenuine.app.repository.validation

/**
 * Fri, 23 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class InternalErrorException(message: String? = null): Exception(
    message ?: "Internal error."
)