package com.example.unsplashcompose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unsplashcompose.MainViewModel
import com.example.unsplashcompose.ui.compoments.UnsplashImage

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
) {
    val image by viewModel.imageToDisplay.collectAsState()
    Box(
        modifier = modifier.padding(16.dp)
    ) {
        UnsplashImage(image = image)
    }
}