package com.example.googleauthapp.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.googleauthapp.domain.model.MessageBarState
import com.example.googleauthapp.domain.model.dto.AuthenticationApiResponse
import com.example.googleauthapp.util.RequestState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            ProfileTopBar(onSave = { }, onDelete = {})
        }
    ) {
        ProfileContent(
            apiResponse = RequestState.Success(AuthenticationApiResponse(success = true)),
            messageBarState = MessageBarState(),
            firstName = "",
            onFirstNameChanged = {},
            lastName = "",
            onLastNameChanged = {},
            email = "",
            profilePicture = "",
            onSignOutClicked = {}
        )
    }

}