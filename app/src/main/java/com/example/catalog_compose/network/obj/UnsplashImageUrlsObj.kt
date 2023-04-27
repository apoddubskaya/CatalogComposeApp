package com.example.catalog_compose.network.obj

import com.google.gson.annotations.SerializedName

data class UnsplashImageUrlsObj(
    @SerializedName("raw") val raw: String,
    @SerializedName("regular") val regular: String,
    @SerializedName("thumb") val thumb: String,
)