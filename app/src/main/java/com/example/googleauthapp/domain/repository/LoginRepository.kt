package com.example.googleauthapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository: Repository {
    suspend fun saveSignedInState(signedIn: Boolean)
    fun readSignedInState(): Flow<Boolean>
}