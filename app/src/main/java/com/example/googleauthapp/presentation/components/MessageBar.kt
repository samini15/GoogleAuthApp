package com.example.googleauthapp.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.googleauthapp.R
import com.example.googleauthapp.domain.model.MessageBarState
import com.example.googleauthapp.ui.theme.ErrorRed
import com.example.googleauthapp.ui.theme.HUGE_PADDING
import com.example.googleauthapp.ui.theme.InfoGreen
import com.example.googleauthapp.ui.theme.LARGE_PADDING
import com.example.googleauthapp.ui.theme.MESSAGE_BAR_HEIGHT
import kotlinx.coroutines.delay
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun MessageBar(messageBarState: MessageBarState) {
    
    var startAnimation by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(key1 = messageBarState) {
        if (messageBarState.error != null) {
            errorMessage = when (messageBarState.error) {
                is SocketTimeoutException -> "Connection Timeout Exception"
                is ConnectException -> "Internet Connection Unavailable"
                else -> "${messageBarState.error.message}"
            }
        }
        startAnimation = true
        delay(4000) // Visible only 4 seconds
        startAnimation = false
    }
    
    AnimatedVisibility(
        visible = (messageBarState.error != null && startAnimation) ||
                (messageBarState.message != null && startAnimation),
        enter = expandVertically(
            animationSpec = tween(300),
            expandFrom = Alignment.Top
        ),
        exit = shrinkVertically(
            animationSpec = tween(300),
            shrinkTowards = Alignment.Top
        )
    ) {
        Message(messageBarState = messageBarState, errorMessage = errorMessage)
    }
}

@Composable
fun Message(
    messageBarState: MessageBarState,
    errorMessage: String = ""
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(
            if (messageBarState.error != null)
                ErrorRed
            else
                InfoGreen
        )
        .padding(horizontal = HUGE_PADDING)
        .heightIn(min = MESSAGE_BAR_HEIGHT),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector =
            if (messageBarState.error != null)
                Icons.Default.Warning
            else
                Icons.Default.Check,
            contentDescription = stringResource(R.string.message_bar_icon_content_description),
            tint = Color.White
        )

        Divider(modifier = Modifier.width(LARGE_PADDING), color = Color.Transparent)

        Text(text =
        if (messageBarState.error != null) errorMessage
        else messageBarState.message.toString(),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun MessageBarPreview() {
    Message(messageBarState = MessageBarState(message = "Successful Login"))
}

@Preview
@Composable
fun MessageBarErrorPreview() {
    Message(messageBarState = MessageBarState(error = SocketTimeoutException()), errorMessage = "Connection Timeout")
}