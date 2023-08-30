package com.example.googleauthapp.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.googleauthapp.R
import com.example.googleauthapp.domain.model.MessageBarState
import com.example.googleauthapp.domain.model.dto.AuthenticationApiResponse
import com.example.googleauthapp.presentation.components.LoginButton
import com.example.googleauthapp.presentation.components.MessageBar
import com.example.googleauthapp.ui.theme.HUGE_PADDING
import com.example.googleauthapp.ui.theme.LARGEST_PADDING
import com.example.googleauthapp.util.RequestState

@Composable
fun ProfileContent(
    apiResponse: RequestState<AuthenticationApiResponse>,
    messageBarState: MessageBarState,
    firstName: String,
    onFirstNameChanged: (String) -> Unit,
    lastName: String,
    onLastNameChanged: (String) -> Unit,
    email: String?,
    profilePicture: String?,
    onSignOutClicked: () -> Unit,
    scaffoldPaddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(
            modifier = Modifier
            .weight(2f)
            .padding(top = scaffoldPaddingValues.calculateTopPadding())
        ) {
            if (apiResponse is RequestState.Loading) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Blue
                )
            } else {
                MessageBar(messageBarState = messageBarState)
            }
        }

        Column(
            modifier = Modifier
                .weight(9f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ProfileCentralContent(
                firstName = firstName,
                onFirstNameChanged = { onFirstNameChanged(it) },
                lastName = lastName,
                onLastNameChanged = { onLastNameChanged(it) },
                email = email,
                profilePicture = profilePicture,
                onSignOutClicked = onSignOutClicked
            )
        }
    }
}

@Composable
fun ProfileCentralContent(
    firstName: String,
    onFirstNameChanged: (String) -> Unit,
    lastName: String,
    onLastNameChanged: (String) -> Unit,
    email: String?,
    profilePicture: String?,
    onSignOutClicked: () -> Unit
) {
    val painter = rememberAsyncImagePainter(model = profilePicture)

    Image(
        modifier = Modifier
            .padding(bottom = LARGEST_PADDING)
            .size(150.dp)
            .clip(CircleShape),
        painter = painter,
        contentDescription = ""
    )

    OutlinedTextField(
        value = firstName,
        onValueChange = { newValue ->
            onFirstNameChanged(newValue)
        },
        label = { Text(text = stringResource(R.string.first_name_textfield_label)) },
        textStyle = MaterialTheme.typography.labelMedium,
        singleLine = true
    )

    OutlinedTextField(
        value = lastName,
        onValueChange = { newValue ->
            onLastNameChanged(newValue)
        },
        label = { Text(text = stringResource(R.string.last_name_textfield_label)) },
        textStyle = MaterialTheme.typography.labelMedium,
        singleLine = true
    )

    OutlinedTextField(
        value = email.orEmpty(),
        onValueChange = {},
        label = { Text(text = stringResource(R.string.email_address_textfield_label)) },
        textStyle = MaterialTheme.typography.labelMedium,
        singleLine = true,
        enabled = false
    )

    LoginButton(
        modifier = Modifier
            .fillMaxWidth(fraction = 0.7f)
            .padding(top = HUGE_PADDING),
        primaryText = "Sign Out",
        secondaryText = "Sign Out",
        onClick = onSignOutClicked
    )
}