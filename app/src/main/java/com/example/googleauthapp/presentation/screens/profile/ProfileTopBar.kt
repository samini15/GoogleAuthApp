package com.example.googleauthapp.presentation.screens.profile

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.googleauthapp.R
import com.example.googleauthapp.presentation.components.CustomAlertDialog
import com.example.googleauthapp.ui.theme.topAppBarBackgroundColor
import com.example.googleauthapp.ui.theme.topAppBarContentColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
    onSave: () -> Unit,
    onDelete: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.profile_top_bar_title), color = MaterialTheme.colorScheme.topAppBarContentColor)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor),
        actions = {
            ProfileTopBarActions(onSave = onSave, onDelete = onDelete)
        }
    )
}

@Composable
fun ProfileTopBarActions(
    onSave: () -> Unit,
    onDelete: () -> Unit
) {
    var openDialog by remember { mutableStateOf(false) }

    CustomAlertDialog(
        title = stringResource(R.string.delete_your_account_alert_dialog_title),
        message = stringResource(R.string.delete_your_account_alert_dialog_message),
        openDialog = openDialog,
        onPositiveClicked = {
            onDelete()
        },
        onDialogClosed = { openDialog = false}
    )
    SaveAction { onSave() }
    DeleteAction { openDialog = true }
}

@Composable
fun SaveAction(onSave: () -> Unit) {
    IconButton(onClick = onSave) {
        Icon(
            painter = painterResource(id = R.drawable.ic_save),
            contentDescription = stringResource(R.string.save_icon_content_description),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteAction(onDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_more),
            contentDescription = stringResource(R.string.more_menu_icon_content_description),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = {
                    Text(text = "Delete Account", style = MaterialTheme.typography.bodySmall)
                },
                onClick = {
                    expanded = false
                    onDelete()
                }
            )
        }
    }
}

@Preview
@Composable
fun ProfileTopBarPreview() {
    ProfileTopBar(onSave = {}, onDelete = {})
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileTopBarDarkPreview() {
    ProfileTopBar(onSave = {}, onDelete = {})
}