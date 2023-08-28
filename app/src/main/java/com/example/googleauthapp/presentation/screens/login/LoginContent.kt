package com.example.googleauthapp.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.googleauthapp.R
import com.example.googleauthapp.domain.model.MessageBarState
import com.example.googleauthapp.presentation.components.LoginButton
import com.example.googleauthapp.presentation.components.MessageBar
import com.example.googleauthapp.ui.theme.HUGE_PADDING
import com.example.googleauthapp.ui.theme.LARGER_PADDING
import com.example.googleauthapp.ui.theme.LARGEST_PADDING
import com.example.googleauthapp.ui.theme.LARGE_PADDING
import com.example.googleauthapp.ui.theme.MEDIUM_PADDING
import com.example.googleauthapp.ui.theme.SMALL_PADDING

@Composable
fun LoginContent(
    signedInState: Boolean,
    messageBarState: MessageBarState,
    onLoginClicked: () -> Unit,
    topPadding: Dp
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.weight(2f).padding(top = topPadding)) {
            MessageBar(messageBarState = messageBarState)
        }
        Column(modifier = Modifier
            .weight(9f)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CentralContent(signedInState = signedInState, onLoginClicked = onLoginClicked)
        }
    }
}

@Composable
fun CentralContent(
    signedInState: Boolean,
    onLoginClicked: () -> Unit
) {
    Image(
        modifier = Modifier
            .padding(bottom = LARGER_PADDING)
            .size(120.dp),
        painter = painterResource(id = R.drawable.ic_google_logo),
        contentDescription = "",
        alignment = Alignment.Center
    )

    Text(
        text = stringResource(id = R.string.sign_in_title),
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.headlineMedium.fontSize
    )
    Text(
        modifier = Modifier
            .alpha(ContentAlpha.medium)
            .fillMaxWidth()
            .padding(start = LARGEST_PADDING, end = LARGEST_PADDING, bottom = HUGE_PADDING, top = MEDIUM_PADDING),
        text = stringResource(id = R.string.sign_in_subtitle),
        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
        textAlign = TextAlign.Center
    )
    LoginButton(
        loadingState = signedInState,
        onClick = onLoginClicked
    )
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    LoginContent(
        signedInState = false,
        messageBarState = MessageBarState(),
        onLoginClicked = {},
        topPadding = 30.dp)
    
}