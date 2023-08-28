package com.example.googleauthapp.domain.repository

import com.example.googleauthapp.domain.model.dto.AuthenticationApiRequest
import com.example.googleauthapp.domain.model.dto.AuthenticationApiResponse
import com.example.googleauthapp.domain.model.dto.UserUpdate
import kotlinx.coroutines.flow.Flow

interface LoginRepository: Repository {
    suspend fun saveSignedInState(signedIn: Boolean)
    fun readSignedInState(): Flow<Boolean>

    suspend fun verifyTokenWithAuthApi(request: AuthenticationApiRequest): AuthenticationApiResponse

    suspend fun getUserInfo(): AuthenticationApiResponse

    suspend fun updateUserInfo(userUpdate: UserUpdate): AuthenticationApiResponse

    suspend fun deleteUserInfo(): AuthenticationApiResponse

    suspend fun logOut(): AuthenticationApiResponse
}