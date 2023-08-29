package com.example.googleauthapp.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CustomAlertDialog(
    title: String,
    message: String,
    positiveButtonText: String? = "Yes",
    negativeButtonText: String? = "No",
    openDialog: Boolean,
    onPositiveClicked: () -> Unit,
    onDialogClosed: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = { onDialogClosed() },
            confirmButton = {
                Button(
                    onClick = {
                        onPositiveClicked()
                        onDialogClosed()
                    }
                ) {
                    Text(text = positiveButtonText.toString())
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { onDialogClosed() }) {
                    Text(text = negativeButtonText.toString())
                }
            },
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = message,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Normal
                )
            }
        )
    }
}