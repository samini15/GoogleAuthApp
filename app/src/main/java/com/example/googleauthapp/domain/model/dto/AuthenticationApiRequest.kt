package com.example.googleauthapp.domain.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationApiRequest(
    val tokenId: String
)
