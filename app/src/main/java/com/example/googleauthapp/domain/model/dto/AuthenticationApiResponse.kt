package com.example.googleauthapp.domain.model.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class AuthenticationApiResponse(
    val success: Boolean,
    val user: User? = null,
    val message: String? = null,

    @Transient
    val error: Exception? = null
)
