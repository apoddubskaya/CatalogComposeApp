package com.example.catalog_compose.data

import java.time.LocalDateTime

data class UnsplashImageDetails(
    val image: UnsplashImage,
    val description: String,
    val location: String,
    val created: LocalDateTime?,
    val downloads: Int,
    val views: Int,
)