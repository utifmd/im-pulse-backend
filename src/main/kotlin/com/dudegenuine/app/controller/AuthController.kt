package com.dudegenuine.app.controller

import com.dudegenuine.app.model.WebResponse
import com.dudegenuine.app.model.auth.AuthRegisterRequest
import com.dudegenuine.app.model.auth.AuthLoginRequest
import com.dudegenuine.app.model.auth.AuthUpdateRequest
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.repository.validation.UnAuthorizationException
import com.dudegenuine.app.service.contract.IAuthService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.signUp(
    service: IAuthService){
    post("api/auth/sign-up") {
        val request: AuthRegisterRequest = try { call.receive() } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
        service.onSignUp(request)

        call.respond(HttpStatusCode.Created)
    }
}
fun Route.signIn(
    service: IAuthService){
    post("api/auth/sign-in"){
        val request: AuthLoginRequest = try { call.receive() } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
        val token = service.onSignIn(request)
        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(token)
        )
    }
}
fun Route.findSecretInfo(){
    authenticate {
        get("api/auth/secret-info"){
            val userId = try {
                call.principal<JWTPrincipal>()?.getClaim("userId", String::class)
            } catch (e: Exception){
                throw UnAuthorizationException(e.localizedMessage)
            }
            call.respond(
                HttpStatusCode.OK,
                WebResponse(userId)
            )
        }
    }
}
fun Route.authenticate(){
    authenticate {
        get("api/auth/authenticate"){
            call.respond(HttpStatusCode.OK)
        }
    }
}
fun Route.findAuth(
    service: IAuthService){
    get("api/auth/{authId}") {
        val authId = call.parameters["authId"] ?: throw BadRequestException()
        val auth = service.findAuth(authId)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(auth)
        )
    }
}
fun Route.patchAuth(
    service: IAuthService){
    put("api/auth") {
        val request: AuthUpdateRequest = try { call.receive() } catch (e: Exception){
            throw BadRequestException(e.localizedMessage)
        }
        val auth = service.updateAuth(request)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(auth)
        )
    }
}
fun Route.isUsernameExist(
    service: IAuthService){
    get("api/auth/is-username-exist/{username}") {
        val username = call.parameters["username"] ?: throw BadRequestException()
        val data = service.isUsernameExist(username)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(data)
        )
    }
}
fun Route.listAuths(
    service: IAuthService){
    get("api/auth") {
        val params = call.request.queryParameters
        val page = params["page"]
            ?. let(Integer::parseInt)
            ?. minus(1)
            ?. toLong()
            ?: throw BadRequestException("page")
        val size = params["size"]
            ?. let(Integer::parseInt)
            ?: throw BadRequestException("size")
        val list = service.listAuths(page to size)

        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(list)
        )
    }
}
fun Route.removeAuth(
    service: IAuthService){
    delete("api/auth/{authId}") {
        val authId = call.parameters["authId"] ?: throw BadRequestException("authId")
        val result = service.deleteAuth(authId)
        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(result)
        )
    }
}