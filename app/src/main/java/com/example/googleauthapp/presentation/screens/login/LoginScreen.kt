package com.example.googleauthapp.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.googleauthapp.domain.model.MessageBarState
import com.example.googleauthapp.ui.theme.topAppBarBackgroundColor
import com.example.googleauthapp.ui.theme.topAppBarContentColor
import com.example.googleauthapp.viewModel.LoginViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    // Observing ViewModel
    val signedInState by loginViewModel.signedInState
    val messageBarState by loginViewModel.messageBarState

    Scaffold(
        topBar = {
            LoginTopBar(
                title = "Sign in",
                backGroundColor = MaterialTheme.colorScheme.topAppBarBackgroundColor,
                contentColor = MaterialTheme.colorScheme.topAppBarContentColor)
        }
    ) {
        LoginContent(
            signedInState = signedInState,
            messageBarState = messageBarState,
            onLoginClicked = {
                loginViewModel.saveSignedInState(signedIn = true)
            }
        )
    }
}