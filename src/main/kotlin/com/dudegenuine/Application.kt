package com.dudegenuine

import com.dudegenuine.di.appModule
import io.ktor.server.application.*
import com.dudegenuine.plugins.*
import org.koin.fileProperties
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    install(Koin){
        fileProperties("/local.properties")

        modules(appModule)
    }
    configureSockets()
    configureRouting()
    configureSecurity()
    configureSerialization()
    configureMonitoring()
}
