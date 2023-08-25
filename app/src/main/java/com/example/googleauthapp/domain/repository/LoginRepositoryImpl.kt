package com.example.googleauthapp.domain.repository

import com.example.googleauthapp.data.dataStore.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataStoreOperations: DataStoreOperations
): LoginRepository {
    override suspend fun saveSignedInState(signedIn: Boolean) =
        dataStoreOperations.saveSignedInState(signedIn = signedIn)

    override fun readSignedInState(): Flow<Boolean> =
        dataStoreOperations.readSignedInState()
}