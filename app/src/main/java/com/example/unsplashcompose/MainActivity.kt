package com.example.unsplashcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.unsplashcompose.ui.MainScreen
import com.example.unsplashcompose.ui.theme.UnsplashComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnsplashComposeTheme {
                MainScreen()
            }
        }
    }
}