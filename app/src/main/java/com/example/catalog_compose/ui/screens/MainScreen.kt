package com.example.catalog_compose.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.catalog_compose.data.UnsplashImage
import com.example.catalog_compose.ui.compoments.list.UnsplashImageList

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    images: LazyPagingItems<UnsplashImage>,
    onImageClick: (UnsplashImage) -> Unit,
) {
    Box(
        modifier = modifier
            .padding(top = 16.dp, start = 20.dp, end = 20.dp)
            .clip(RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp))
    ) {
        UnsplashImageList(images = images, onImageClick = onImageClick)
    }
}