package com.dudegenuine.app.repository.dependency

import com.dudegenuine.app.model.security.SaltedHash

/**
 * Tue, 13 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
interface IHashDependency {
    fun generateSaltedHash(password: String, saltLength: Int = 32): SaltedHash
    fun verify(password: String, saltedHash: SaltedHash): Boolean
}