package com.example.googleauthapp.presentation.common

import android.app.Activity
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.googleauthapp.util.Constants
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes

@Composable
fun StartActivityForResult(
    key: Any,
    onResultReceived: (String) -> Unit,
    onDialogDismissed: () -> Unit,
    launcher: (ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) -> Unit
) {
    val activity = LocalContext.current as Activity

    val activityLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult()) { result ->
        try {
            if (result.resultCode == Activity.RESULT_OK) {
                val oneTapClient = Identity.getSignInClient(activity)
                val credentials = oneTapClient.getSignInCredentialFromIntent(result.data)
                val tokenId = credentials.googleIdToken
                tokenId?.let {
                    onResultReceived(tokenId)
                }
            } else {
                onDialogDismissed()
            }
        } catch (e: ApiException) {
            when (e.statusCode) {
                CommonStatusCodes.CANCELED -> {
                    Log.e("StartActivityForResult", "One Tap Dialog Canceled.")
                    onDialogDismissed()
                }
                CommonStatusCodes.NETWORK_ERROR -> {
                    Log.e("StartActivityForResult", "One Tap Dialog Network Error.")
                    onDialogDismissed()
                }
                else -> {
                    Log.e("StartActivityForResult", "${e.message}")
                    onDialogDismissed()
                }
            }
        }
    }

    LaunchedEffect(key1 = key) {
        launcher(activityLauncher)
    }
}
fun signIn(
    activity: Activity,
    launchActivityResult: (IntentSenderRequest) -> Unit,
    accountNotFound: () -> Unit
) {
    onTapSign(
        activity = activity,
        launchActivityResult = launchActivityResult,
        accountNotFound = accountNotFound,
        filterByAuthorizedAccounts = true,
        autoSelectEnabled = true,
        isSigningUp = false
    )
}

fun onTapSign(
    activity: Activity,
    launchActivityResult: (IntentSenderRequest) -> Unit,
    accountNotFound: () -> Unit,
    filterByAuthorizedAccounts: Boolean,
    autoSelectEnabled: Boolean,
    isSigningUp: Boolean
) {
    val oneTapClient = Identity.getSignInClient(activity)
    val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
            .setSupported(true)
            .setServerClientId(Constants.GOOGLE_CLIENT_ID)
            .setFilterByAuthorizedAccounts(filterByAuthorizedAccounts)
            .build()
        )
        .setAutoSelectEnabled(autoSelectEnabled)
        .build()

    oneTapClient.beginSignIn(signInRequest)
        .addOnSuccessListener {  result ->
            try {
                launchActivityResult(
                    IntentSenderRequest.Builder(result.pendingIntent.intentSender).build()
                )
            } catch (e: Exception) {
                Log.e("SignIn", "Could not perform One Tape Connection : ${e.message}")
            }
        }
        .addOnFailureListener { exception ->
            Log.e("SignIn", "Signing Up : ${exception.message}")
            if (isSigningUp)
                accountNotFound()
            else
                // Sign Up Google -> Showing all accounts on the device
                onTapSign(
                    activity = activity,
                    launchActivityResult = launchActivityResult,
                    accountNotFound = accountNotFound,
                    filterByAuthorizedAccounts = false, // All accounts on device
                    autoSelectEnabled = false,
                    isSigningUp = true
                )
        }
}