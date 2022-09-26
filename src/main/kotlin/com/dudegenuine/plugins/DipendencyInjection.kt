package com.dudegenuine.plugins

import com.dudegenuine.di.*
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.koin.fileProperties
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

/**
 * Thu, 08 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Application.configureDependencyInjection(){
    install(Koin){
        fileProperties("/local.properties")

        modules(appModule)
        modules(mapperModule)
        modules(repositoryModule)
        modules(serviceModule)
        modules(dependencyModule)
    }
    val database: Database by inject()
    println("database vendor ${database.vendor}")
}