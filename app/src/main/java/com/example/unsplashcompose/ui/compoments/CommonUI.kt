package com.example.unsplashcompose.ui.compoments

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.unsplashcompose.R
import com.example.unsplashcompose.data.UnsplashImage

@Composable
fun UnsplashImage(image: UnsplashImage) {
    AsyncImage(
        model = image.url,
        contentDescription = "",
        placeholder = painterResource(id = R.drawable.image_placeholder),
        error = painterResource(id = R.drawable.image_error)
    )
}