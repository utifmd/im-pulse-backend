package com.dudegenuine.di

import com.dudegenuine.app.model.security.AuthTokenConfig
import com.typesafe.config.ConfigFactory
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module
/**
 * Wed, 31 Aug 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/

val appModule = module {
    single {
        val url = getProperty("BASE_URL") as String
        val user = getProperty("USERNAME") as String
        val password = getProperty("PASSWORD") as String
        Database.connect(url, user = user, password = password)
    }
    single{
        val config = ConfigFactory.load()
        AuthTokenConfig(
            audience = config.getString("jwt.audience"),
            issuer = config.getString("jwt.issuer"),
            realm = config.getString("jwt.realm"),
            expiresIn = 365L * 1000L * 60L * 60L * 24L,
            secret = getProperty("JWT_SECRET") as String
        )
    }
}
