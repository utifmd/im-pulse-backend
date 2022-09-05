package com.dudegenuine.app.model

import kotlinx.serialization.Serializable

/**
 * Thu, 01 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
@Serializable
data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val about: String?,
    val profileStatus: String?,
    val profilePicture: Image?,
    val region: String?,
    val auth: Auth?,
    val level: String?, //Level?,
    val verifier: List<Verifier>,
    val tokens: List<Token>,
    val createdAt: String,
    val updatedAt: String?
)
