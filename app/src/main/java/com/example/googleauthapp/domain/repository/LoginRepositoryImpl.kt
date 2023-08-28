package com.example.googleauthapp.domain.repository

import com.example.googleauthapp.data.dataStore.DataStoreOperations
import com.example.googleauthapp.data.remote.AuthenticationApi
import com.example.googleauthapp.domain.model.dto.AuthenticationApiRequest
import com.example.googleauthapp.domain.model.dto.AuthenticationApiResponse
import com.example.googleauthapp.domain.model.dto.UserUpdate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataStoreOperations: DataStoreOperations,
    private val authenticationApiService: AuthenticationApi
): LoginRepository {
    override suspend fun saveSignedInState(signedIn: Boolean) =
        dataStoreOperations.saveSignedInState(signedIn = signedIn)

    override fun readSignedInState(): Flow<Boolean> =
        dataStoreOperations.readSignedInState()


    override suspend fun verifyTokenWithAuthApi(request: AuthenticationApiRequest): AuthenticationApiResponse =
        try {
            authenticationApiService.verifyToken(request = request)
        } catch (e: Exception) {
            AuthenticationApiResponse(success = false, error = e)
        }

    override suspend fun getUserInfo(): AuthenticationApiResponse =
        try {
            authenticationApiService.getUserInfo()
        } catch (e: Exception) {
            AuthenticationApiResponse(success = false, error = e)
        }

    override suspend fun updateUserInfo(userUpdate: UserUpdate): AuthenticationApiResponse =
        try {
            authenticationApiService.updateUserInfo(userUpdate)
        } catch (e: Exception) {
            AuthenticationApiResponse(success = false, error = e)
        }

    override suspend fun deleteUserInfo(): AuthenticationApiResponse =
        try {
            authenticationApiService.deleteUserInfo()
        } catch (e: Exception) {
            AuthenticationApiResponse(success = false, error = e)
        }

    override suspend fun logOut(): AuthenticationApiResponse =
        try {
            authenticationApiService.signOut()
        } catch (e: Exception) {
            AuthenticationApiResponse(success = false, error = e)
        }
}