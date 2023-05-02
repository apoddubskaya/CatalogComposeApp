package com.example.catalog_compose.ui.compoments.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.catalog_compose.R
import com.example.catalog_compose.data.UnsplashImage
import com.example.catalog_compose.ui.compoments.UnsplashImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material3.shimmer
import com.google.accompanist.placeholder.placeholder

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UnsplashImageGrid(
    modifier: Modifier = Modifier,
    content: LazyStaggeredGridScope.() -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = content
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun UnsplashImageListLoading(
    modifier: Modifier = Modifier
) {
    UnsplashImageGrid(modifier = modifier) {
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
            text = stringResource(id = R.string.load_error),
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UnsplashImageList(
    modifier: Modifier = Modifier,
    images: LazyPagingItems<UnsplashImage>,
    onImageClick: (UnsplashImage) -> Unit,
) {
    when (images.loadState.refresh) {
        is LoadState.NotLoading -> UnsplashImageGrid(modifier = modifier) {
            items(images.itemCount) { index ->
                images[index]?.let { UnsplashImage(modifier = modifier, image = it, onImageClick = onImageClick) }
            }
            when (images.loadState.append) {
                is LoadState.Loading -> item(span = StaggeredGridItemSpan.FullLine) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }

                is LoadState.Error -> item(span = StaggeredGridItemSpan.FullLine) {
                    UnsplashImageListError(modifier = modifier) { images.retry() }
                }

                else -> {}
            }
        }

        is LoadState.Loading -> UnsplashImageListLoading(modifier = modifier)
        is LoadState.Error -> UnsplashImageListError(modifier = modifier) { images.retry() }
    }
}