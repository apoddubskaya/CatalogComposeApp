package com.example.catalog_compose.ui.compoments.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.catalog_compose.data.UnsplashImage
import com.example.catalog_compose.ui.compoments.UnsplashImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material3.shimmer
import com.google.accompanist.placeholder.placeholder

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

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun UnsplashImageListLoading(
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(10) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(if (it % 3 == 0) 96.dp else 224.dp)
                    .placeholder(
                        color = Color.LightGray,
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                    ),
            )
        }
    }
}

@Preview
@Composable
fun UnsplashImageListError(
    modifier: Modifier = Modifier,
    tryAgainAction: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = modifier,
            text = "Error occurred while fetching data. Check your internet connection and try to reload.",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Spacer(modifier = modifier.height(16.dp))
        Button(
            modifier = modifier
                .height(48.dp),
            onClick = tryAgainAction
        ) {
            Text(
                text = "try again".uppercase()
            )
        }
    }
}

@Composable
fun UnsplashImageListPaging(modifier: Modifier = Modifier, images: LazyPagingItems<UnsplashImage>) {
    when (images.loadState.refresh) {
        is LoadState.Loading -> UnsplashImageListLoading(modifier = modifier)
        is LoadState.Error -> UnsplashImageListError(modifier = modifier) { images.retry() }
        else -> UnsplashImageList(modifier = modifier, images = images)
    }
}