package com.dudegenuine.plugins

import com.dudegenuine.di.appModule
import com.dudegenuine.di.mapperModule
import com.dudegenuine.di.repositoryModule
import com.dudegenuine.di.serviceModule
import io.ktor.server.application.*
import org.koin.fileProperties
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
    }
}