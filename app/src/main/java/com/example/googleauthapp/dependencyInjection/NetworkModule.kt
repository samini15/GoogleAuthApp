package com.example.googleauthapp.dependencyInjection

import com.example.googleauthapp.util.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.JavaNetCookieJar
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.net.CookieManager
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCookieManager(): CookieManager = CookieManager()

    @Provides
    @Singleton
    fun provideHttpClient(cookieManager: CookieManager): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .cookieJar(JavaNetCookieJar(cookieManager)) // Attach cookie to each request
            .build()

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType = contentType))
            .build()
    }
}