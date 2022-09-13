package com.dudegenuine.app.repository.dependency

import com.dudegenuine.app.model.security.SaltedHash
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom

/**
 * Tue, 13 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
class HashDependency: IHashDependency {
    override fun generateSaltedHash(password: String, saltLength: Int): SaltedHash {
        val rawSalt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLength)
        val saltString = Hex.encodeHexString(rawSalt)
        val hashedPassword = DigestUtils.sha256Hex("$saltString$password")

        return SaltedHash(hashedPassword, saltString)
    }

    override fun verify(password: String, saltedHash: SaltedHash): Boolean {
        val (hashedPassword, saltString) = saltedHash
        return DigestUtils.sha256Hex(saltString + password) == hashedPassword
    }
}