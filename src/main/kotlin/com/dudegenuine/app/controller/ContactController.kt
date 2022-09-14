package com.dudegenuine.app.controller

import com.dudegenuine.app.model.WebResponse
import com.dudegenuine.app.model.contact.ContactCreateRequest
import com.dudegenuine.app.model.contact.ContactUpdateRequest
import com.dudegenuine.app.repository.validation.BadRequestException
import com.dudegenuine.app.service.contract.IContactService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Wed, 14 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
fun Route.addContact(
    service: IContactService){
    get("api/contacts"){
        val request: ContactCreateRequest = try { call.receive() } catch (e: Exception){
            throw BadRequestException("contactId")
        }
        val response = service.addContact(request)
        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(response)
        )
    }
}
fun Route.putContact(service: IContactService){
    put("api/contacts"){
        val request: ContactUpdateRequest = try { call.receive() } catch (e: Exception){
            throw BadRequestException("contactId")
        }
        val response = service.putContact(request)
        call.respond(
            status = HttpStatusCode.OK,
            message = WebResponse(response)
        )
    }
}