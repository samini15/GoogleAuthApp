package com.example.googleauthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.googleauthapp.navigation.SetUpNavGraph
import com.example.googleauthapp.ui.theme.GoogleAuthAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleAuthAppTheme {
                val navController = rememberNavController()
                SetUpNavGraph(navController = navController)
            }
        }
    }
}