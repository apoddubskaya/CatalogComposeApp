package com.example.catalog_compose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.catalog_compose.MainViewModel
import com.example.catalog_compose.ui.compoments.list.UnsplashImageListPaging

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
) {
    val images = viewModel.images.collectAsLazyPagingItems()
    Box(
        modifier = modifier
            .padding(top = 16.dp, start = 20.dp, end = 20.dp)
            .clip(RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp))
    ) {
        UnsplashImageListPaging(images = images)
    }
}