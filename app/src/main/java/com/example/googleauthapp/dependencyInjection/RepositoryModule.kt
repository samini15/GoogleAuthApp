package com.example.googleauthapp.dependencyInjection

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.googleauthapp.data.dataStore.DataStoreOperations
import com.example.googleauthapp.data.dataStore.DataStoreOperationsImpl
import com.example.googleauthapp.data.remote.AuthenticationApi
import com.example.googleauthapp.domain.repository.LoginRepository
import com.example.googleauthapp.domain.repository.LoginRepositoryImpl
import com.example.googleauthapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStorePreferences(
        @ApplicationContext context: Context
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(Constants.PREFERENCES_NAME)
        }

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        dataStore: DataStore<Preferences>
    ): DataStoreOperations = DataStoreOperationsImpl(dataStore = dataStore)

    @Provides
    @Singleton
    fun provideLoginRepository(
        dataStoreOperations: DataStoreOperations,
        api: AuthenticationApi
    ): LoginRepository = LoginRepositoryImpl(dataStoreOperations = dataStoreOperations, authenticationApiService = api)
}