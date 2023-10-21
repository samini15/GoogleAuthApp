package com.example.googleauthapp.presentation.screens.profile

import android.annotation.SuppressLint
import android.app.Activity
import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.googleauthapp.domain.model.dto.AuthenticationApiRequest
import com.example.googleauthapp.domain.model.dto.AuthenticationApiResponse
import com.example.googleauthapp.navigation.Screen
import com.example.googleauthapp.presentation.common.StartActivityForResult
import com.example.googleauthapp.util.RequestState
import com.example.googleauthapp.viewModel.ProfileViewModel
import com.google.android.gms.auth.api.identity.Identity

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    // ViewModel Observation
    val apiResponse by profileViewModel.apiResponse
    val clearSessionResponse by profileViewModel.clearSessionResponse
    val messageBarState by profileViewModel.messageBarState

    val currentUser by profileViewModel.currentUser
    val firstName by profileViewModel.firstName
    val lastName by profileViewModel.lastName


    Scaffold(
        topBar = {
            ProfileTopBar(
                onSave = { profileViewModel.updateUserInfo() },
                onDelete = { profileViewModel.deleteUser() }
            )
        }
    ) {
        ProfileContent(
            apiResponse = apiResponse,
            messageBarState = messageBarState,
            firstName = firstName,
            onFirstNameChanged = {
                profileViewModel.updateFirstName(newValue = it)
            },
            lastName = lastName,
            onLastNameChanged = {
                profileViewModel.updateLastName(newValue = it)
            },
            email = currentUser?.email,
            profilePicture = currentUser?.profilePicture,
            onSignOutClicked = {
                profileViewModel.clearSession()
            },
            scaffoldPaddingValues = it
        )
    }

    val activity = LocalContext.current as Activity

    LaunchedEffect(key1 = clearSessionResponse) {
        if (clearSessionResponse is RequestState.Success &&
            (clearSessionResponse as RequestState.Success<AuthenticationApiResponse>).data.success) {
            val oneTapClient = Identity.getSignInClient(activity)
            oneTapClient.signOut()
            profileViewModel.saveSignedInState(signedIn = false)
            navigateBackToLoginScreen(navController = navController)
        }
    }
}

private fun navigateBackToLoginScreen(
    navController: NavHostController
) {
    navController.navigate(route = Screen.Login.route) {
        popUpTo(route = Screen.Profile.route) { // Remove Profile from back stack
            inclusive = true
        }

    }
}