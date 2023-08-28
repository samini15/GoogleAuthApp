package com.example.googleauthapp.domain.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val email: String,
    val profilePicture: String
)
