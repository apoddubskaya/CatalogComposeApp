package com.example.catalog_compose.ui.compoments

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.example.catalog_compose.R
import com.example.catalog_compose.data.UnsplashImage
import com.ondev.imageblurkt_lib.AsyncImageBlurHash
import com.ondev.imageblurkt_lib.ImageBlurHashModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun UnsplashImage(modifier: Modifier = Modifier, image: UnsplashImage) {
    AsyncImageBlurHash(
        modifier = modifier.aspectRatio(image.width.toFloat() / image.height),
        model = ImageBlurHashModel(data = image.url, blurHash = image.blurHash),
        contentDescription = image.description,
        notImageFoundRes = R.drawable.image_error
    )
}