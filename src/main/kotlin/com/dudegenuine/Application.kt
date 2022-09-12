package com.dudegenuine

import io.ktor.server.application.*
import com.dudegenuine.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureDependencyInjection()
    configureSockets()
    configureRouting()
    configureSecurity()
    configureSerialization()
    configureMonitoring()
    configureException()
}
/*
* TODO
* */