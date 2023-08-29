package com.example.googleauthapp.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.googleauthapp.domain.model.MessageBarState
import com.example.googleauthapp.domain.model.dto.AuthenticationApiResponse
import com.example.googleauthapp.util.RequestState
import com.example.googleauthapp.viewModel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    // ViewModel Observation
    val apiResponse by profileViewModel.apiResponse
    val messageBarState by profileViewModel.messageBarState

    val currentUser by profileViewModel.currentUser
    val firstName by profileViewModel.firstName
    val lastName by profileViewModel.lastName


    Scaffold(
        topBar = {
            ProfileTopBar(onSave = { }, onDelete = {})
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
            onSignOutClicked = {}
        )
    }

}