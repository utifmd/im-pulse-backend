package com.dudegenuine

import com.dudegenuine.app.entities.Auths
import com.dudegenuine.di.appModule
import com.dudegenuine.di.mapperModule
import com.dudegenuine.di.repositoryModule
import com.dudegenuine.di.serviceModule
import io.ktor.server.application.*
import com.dudegenuine.plugins.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.fileProperties
import org.koin.ktor.plugin.Koin
import java.util.*

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
