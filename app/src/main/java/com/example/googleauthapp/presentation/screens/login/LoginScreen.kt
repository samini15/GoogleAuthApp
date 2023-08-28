package com.example.googleauthapp.presentation.screens.login

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.googleauthapp.domain.model.dto.AuthenticationApiRequest
import com.example.googleauthapp.domain.model.dto.AuthenticationApiResponse
import com.example.googleauthapp.navigation.Screen
import com.example.googleauthapp.presentation.common.StartActivityForResult
import com.example.googleauthapp.presentation.common.signIn
import com.example.googleauthapp.ui.theme.topAppBarBackgroundColor
import com.example.googleauthapp.ui.theme.topAppBarContentColor
import com.example.googleauthapp.util.RequestState
import com.example.googleauthapp.viewModel.LoginViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    // Observing ViewModel
    val signedInState by loginViewModel.signedInState.collectAsStateWithLifecycle()
    val messageBarState by loginViewModel.messageBarState
    val apiResponse by loginViewModel.apiResponse

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
            },
            topPadding = it.calculateTopPadding()
        )
    }

    val activity = LocalContext.current as Activity
    StartActivityForResult(
        key = signedInState,
        onResultReceived = { tokenId ->
            loginViewModel.verifyTokenWithAuthApi(AuthenticationApiRequest(tokenId = tokenId))
        },
        onDialogDismissed = {
            loginViewModel.saveSignedInState(signedIn = false)
        }
    ) { launcher ->
        if (signedInState) {
            signIn(
                activity = activity,
                launchActivityResult = { intentSenderRequest ->
                    launcher.launch(intentSenderRequest)
                },
                accountNotFound = {
                    loginViewModel.apply {
                        saveSignedInState(signedIn = false)
                        updateMessageBarState()
                    }
                }
            )
        }
    }

    LaunchedEffect(key1 = apiResponse) {
        when (apiResponse) {
            is RequestState.Success -> {
                val responseOk = (apiResponse as RequestState.Success<AuthenticationApiResponse>).data.success
                Log.d("LoginScreen", responseOk.toString())
                if (responseOk) {
                    navigateToProfileScreen(navController = navController)
                } else {
                    loginViewModel.saveSignedInState(signedIn = false)
                }
            }
            else -> {}
        }
    }
}

private fun navigateToProfileScreen(
    navController: NavHostController
) {
    navController.navigate(Screen.Profile.route) {
        popUpTo(Screen.Login.route) {
            inclusive = true
        }
    }
}