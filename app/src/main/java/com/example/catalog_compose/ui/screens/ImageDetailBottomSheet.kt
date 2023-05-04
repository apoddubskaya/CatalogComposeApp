package com.example.catalog_compose.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catalog_compose.R
import com.example.catalog_compose.data.UnsplashImage

@Preview
@Composable
fun ImageDetailBottomSheet(
    image: UnsplashImage? = null
) {
    Column(Modifier.padding(horizontal = 24.dp)) {
        Spacer(modifier = Modifier.height(56.dp))
        Text(text = "Laying in the sun in Puerto Rico", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            TitleTextDetail(title = stringResource(id = R.string.image_detail_screen_views), text = "1258")
            Spacer(modifier = Modifier.width(40.dp))
            TitleTextDetail(title = stringResource(id = R.string.image_detail_screen_downloads), text = "24")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.image_detail_screen_special_information),
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        IconTextDetail(text = "Puerto Rico", iconRes = R.drawable.ic_profile)
        Spacer(modifier = Modifier.height(8.dp))
        IconTextDetail(text = "Published 10h ago", iconRes = R.drawable.ic_phone)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.image_detail_screen_licence),
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun IconTextDetail(
    text: String,
    @DrawableRes iconRes: Int
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