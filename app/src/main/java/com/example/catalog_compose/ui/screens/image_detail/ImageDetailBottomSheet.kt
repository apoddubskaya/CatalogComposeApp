package com.example.catalog_compose.ui.screens.image_detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.catalog_compose.R
import com.example.catalog_compose.data.UnsplashImageDetails
import com.example.catalog_compose.ui.util.Request
import java.time.format.DateTimeFormatter

@Composable
fun ImageDetailBottomSheetRoute(
    viewModel: ImageDetailViewModel,
) {
    val uiState by viewModel.imageDetailsUiState.collectAsStateWithLifecycle()
    ImageDetailBottomSheet(imageDetails = (uiState.imageDetailsRequest as? Request.Success)?.data)
}

@Preview
@Composable
fun ImageDetailBottomSheet(
    imageDetails: UnsplashImageDetails? = null,
) {
    Column(Modifier.padding(horizontal = 24.dp)) {
        Spacer(modifier = Modifier.height(56.dp))
        imageDetails?.let { details ->
            Description(details.description)
            Spacer(modifier = Modifier.height(24.dp))
            ViewsAndDownloads(details)
            Spacer(modifier = Modifier.height(24.dp))
            SpecialInformation(details)
        }
        Licence()
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun Licence() {
    Text(
        text = stringResource(id = R.string.image_detail_screen_licence),
        fontSize = 16.sp,
    )
}

@Composable
fun Description(description: String) {
    Text(
        text = description.takeIf { it.isNotBlank() }
            ?: stringResource(id = R.string.image_detail_screen_description_default),
        fontSize = 24.sp,
    )
}

@Composable
fun ViewsAndDownloads(details: UnsplashImageDetails) {
    Row {
        TitleTextDetail(
            title = stringResource(id = R.string.image_detail_screen_views), text = details.views.toString()
        )
        Spacer(modifier = Modifier.width(40.dp))
        TitleTextDetail(
            title = stringResource(id = R.string.image_detail_screen_downloads), text = details.downloads.toString()
        )
    }
}

@Composable
fun SpecialInformation(details: UnsplashImageDetails) {
    if (details.location.isNotBlank() || details.created != null) {
        Text(
            text = stringResource(id = R.string.image_detail_screen_special_information),
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
    if (details.location.isNotBlank()) {
        IconTextDetail(text = details.location, iconRes = R.drawable.ic_profile)
        Spacer(modifier = Modifier.height(8.dp))
    }
    if (details.created != null) {
        IconTextDetail(
            text = stringResource(
                id = R.string.image_detail_screen_location_text,
                DateTimeFormatter.ofPattern("MMMM dd, yyyy | hh:mma").format(details.created)
            ), iconRes = R.drawable.ic_phone
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun IconTextDetail(
    text: String,
    @DrawableRes iconRes: Int,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(id = iconRes), contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 16.sp)
    }
}

@Composable
private fun TitleTextDetail(
    title: String,
    text: String,
) {
    Column {
        Text(text = title, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, fontSize = 16.sp)
    }
}