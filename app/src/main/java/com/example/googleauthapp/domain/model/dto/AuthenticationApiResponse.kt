package com.example.googleauthapp.domain.model.dto

import kotlinx.serialization.Transient

data class AuthenticationApiResponse(
    val success: Boolean,
    val user: User? = null,
    val message: String? = null,

    @Transient
    val error: Exception? = null
)
