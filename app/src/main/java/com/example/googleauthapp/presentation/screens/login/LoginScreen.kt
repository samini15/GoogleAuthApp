package com.example.googleauthapp.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.googleauthapp.domain.model.MessageBarState
import com.example.googleauthapp.ui.theme.topAppBarBackgroundColor
import com.example.googleauthapp.ui.theme.topAppBarContentColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            LoginTopBar(
                title = "Sign in",
                backGroundColor = MaterialTheme.colorScheme.topAppBarBackgroundColor,
                contentColor = MaterialTheme.colorScheme.topAppBarContentColor)
        }
    ) {
        LoginContent(
            signedInState = false,
            messageBarState = MessageBarState(),
            onLoginClicked = {}
        )
    }
}