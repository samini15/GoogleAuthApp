package com.example.googleauthapp.data.remote

import com.example.googleauthapp.domain.model.dto.AuthenticationApiRequest
import com.example.googleauthapp.domain.model.dto.AuthenticationApiResponse
import com.example.googleauthapp.domain.model.dto.UserUpdate
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthenticationApi {
    @POST("/token_verification")
    suspend fun verifyToken(@Body request: AuthenticationApiRequest): AuthenticationApiResponse

    @GET("/get_user")
    suspend fun getUserInfo(): AuthenticationApiResponse

    @PUT("/update_user")
    suspend fun updateUserInfo(@Body userUpdate: UserUpdate): AuthenticationApiResponse

    @DELETE("/delete_user")
    suspend fun deleteUserInfo(): AuthenticationApiResponse

    @GET("/sign_out")
    suspend fun signOut(): AuthenticationApiResponse
}