package com.dudegenuine.di

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
}
/*

interface IDatabase{
    fun connect(): Database
}

class Database(
    private val urlUsrPwd: Triple<String, String, String>): IDatabase {
    override fun connect(): Database {
        val (url, user, password) = urlUsrPwd

        return Database.connect(url, user = user, password = password)
    }

}*/
