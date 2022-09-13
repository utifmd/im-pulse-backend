package com.dudegenuine.plugins

import com.dudegenuine.app.model.ExceptionResponse
import com.dudegenuine.app.repository.validation.AlreadyExistException
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.NotFoundException
import com.dudegenuine.app.repository.validation.UnAuthorizationException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

/**
 * Thu, 08 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Application.configureException(){
    install(StatusPages){
        exception<Throwable> { call, cause ->
            when(cause){
                is NotFoundException -> call.respond(
                    HttpStatusCode.NotFound,
                    ExceptionResponse(
                        code = HttpStatusCode.NotFound.value,
                        message = cause.localizedMessage
                    )
                )
                is BadRequestException -> call.respond(
                    HttpStatusCode.BadRequest,
                    ExceptionResponse(
                        code = HttpStatusCode.BadRequest.value,
                        message = cause.localizedMessage
                    )
                )
                is AlreadyExistException -> call.respond(
                    HttpStatusCode.BadRequest,
                    ExceptionResponse(
                        code = HttpStatusCode.BadRequest.value,
                        message = cause.localizedMessage
                    )
                )
                is UnAuthorizationException -> call.respond(
                    HttpStatusCode.Unauthorized,
                    ExceptionResponse(
                        code = HttpStatusCode.Unauthorized.value,
                        message = cause.localizedMessage
                    )
                )
            }
        }
        status(
            HttpStatusCode.InternalServerError,
            HttpStatusCode.BadGateway) { call, statusCode ->

            when(statusCode) {
                HttpStatusCode.InternalServerError -> {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        ExceptionResponse(
                            code = HttpStatusCode.InternalServerError.value,
                            message = "Oops! internal server error at our end"
                        )
                    )
                }
                HttpStatusCode.BadGateway -> {
                    call.respond(
                        HttpStatusCode.BadGateway,
                        ExceptionResponse(
                            code = HttpStatusCode.BadGateway.value,
                            message = "Oops! We got a bad gateway. Fixing it. Hold on!"
                        )
                    )
                }
            }
        }
    }
}