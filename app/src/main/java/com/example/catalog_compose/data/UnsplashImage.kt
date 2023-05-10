package com.example.catalog_compose.data

data class UnsplashImage(
    val id: String,
    val altDescription: String,
    val url: String,
    val blurHash: String,
    val width: Int,
    val height: Int,
)