package com.example.googleauthapp.presentation.screens.login

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.googleauthapp.ui.theme.topAppBarBackgroundColor
import com.example.googleauthapp.ui.theme.topAppBarContentColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTopBar(
    title: String,
    backGroundColor: Color,
    contentColor: Color
) {
    TopAppBar(
        title = {
            Text(text = title, color = contentColor)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = backGroundColor)
    )
}

@Preview
@Composable
fun LoginTopBarPreview() {
    LoginTopBar(
        title = "Sign in",
        backGroundColor = MaterialTheme.colorScheme.topAppBarBackgroundColor,
        contentColor = MaterialTheme.colorScheme.topAppBarContentColor
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LoginTopBarPreviewDark() {
    LoginTopBar(
        title = "Sign in",
        backGroundColor = MaterialTheme.colorScheme.topAppBarBackgroundColor,
        contentColor = MaterialTheme.colorScheme.topAppBarContentColor
    )
}