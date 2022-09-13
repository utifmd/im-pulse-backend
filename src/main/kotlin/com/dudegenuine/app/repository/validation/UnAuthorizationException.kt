package com.dudegenuine.app.repository.validation

/**
 * Tue, 13 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class UnAuthorizationException(message: String? = null): Exception(
    message?.let{ "Authorization of $it was failed." } ?: "Authorization failed."
)