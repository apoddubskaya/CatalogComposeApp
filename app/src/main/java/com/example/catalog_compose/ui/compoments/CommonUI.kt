package com.example.catalog_compose.ui.compoments

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.catalog_compose.R
import com.example.catalog_compose.data.UnsplashImage

@Composable
fun UnsplashImage(modifier: Modifier = Modifier, image: UnsplashImage) {
    AsyncImage(
        modifier = modifier,
        model = image.url,
        contentDescription = image.description,
        placeholder = painterResource(id = R.drawable.image_placeholder),
        error = painterResource(id = R.drawable.image_error)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UnsplashImageList(modifier: Modifier = Modifier, images: LazyPagingItems<UnsplashImage>) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(images.itemCount) { index ->
            images[index]?.let { UnsplashImage(modifier = modifier, image = it) }
        }
    }
}

@Composable
fun UnsplashImageListPaging(modifier: Modifier = Modifier, images: LazyPagingItems<UnsplashImage>) {
    when (images.loadState.refresh) {
        is LoadState.Loading -> {}
        is LoadState.Error -> {}
        else -> {
            UnsplashImageList(modifier = modifier, images = images)
        }
    }
}