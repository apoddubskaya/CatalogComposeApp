package com.example.unsplashcompose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unsplashcompose.MainViewModel
import com.example.unsplashcompose.ui.compoments.UnsplashImageList

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
) {
    val images by viewModel.images.collectAsState()
    Box(
        modifier = modifier
            .padding(top = 16.dp, start = 20.dp, end = 20.dp)
            .clip(RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp))
    ) {
        UnsplashImageList(images = images)
    }
}