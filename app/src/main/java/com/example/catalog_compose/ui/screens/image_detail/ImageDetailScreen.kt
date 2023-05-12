package com.example.catalog_compose.ui.screens.image_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.annotation.ExperimentalCoilApi
import com.example.catalog_compose.R
import com.example.catalog_compose.ui.compoments.UnsplashImage
import com.example.catalog_compose.ui.util.Request
import com.ondev.imageblurkt_lib.ImageBlurHashModel
import com.ondev.imageblurkt_lib.ImageBlurOnly

@Composable
fun ImageDetailRoute(
    onBackClick: () -> Unit,
    onAboutClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ImageDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.imageDetailsUiState.collectAsStateWithLifecycle()

    ImageDetailScreen(
        modifier = modifier,
        uiState = uiState,
        onShareClick = onShareClick,
        onAboutClick = onAboutClick,
        onBackClick = onBackClick,
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageDetailScreen(
    modifier: Modifier = Modifier,
    uiState: ImageDetailUiState,
    onBackClick: () -> Unit = {},
    onAboutClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
) = with(uiState) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (imageDetailsRequest is Request.Success) {
            UnsplashImage(
                modifier = modifier.fillMaxSize(),
                image = imageDetailsRequest.data.image,
            )
            ImageDetailScreenBottomButtons(onAboutClick = onAboutClick, onShareClick = onShareClick)
        } else {
            ImageBlurOnly(
                modifier = Modifier.fillMaxSize(),
                data = ImageBlurHashModel(blurHash = uiState.imageBlurHash, data = ""),
            )
        }
        ImageDetailScreenBackButton(onBackClick = onBackClick)
    }
}

@Composable
fun ImageDetailScreenBottomButtons(
    onAboutClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            ImageDetailScreenButton(
                text = stringResource(id = R.string.image_detail_screen_about_image), onClick = onAboutClick
            )
            Spacer(modifier = Modifier.width(16.dp))
            ImageDetailScreenButton(
                text = stringResource(id = R.string.image_detail_screen_share_image), onClick = onShareClick
            )
        }
    }
}

@Composable
fun ImageDetailScreenButton(
    text: String = "",
    onClick: () -> Unit = {},
) = Button(
    modifier = Modifier
        .width(148.dp)
        .height(48.dp),
    onClick = onClick
) { Text(text = text, fontSize = 16.sp) }

@Composable
fun ImageDetailScreenBackButton(
    onBackClick: () -> Unit = {},
) = IconButton(
    modifier = Modifier
        .padding(16.dp)
        .size(24.dp),
    onClick = onBackClick
) {
    Icon(
        imageVector = Icons.Default.ArrowBack, contentDescription = "tap to go back"
    )
}