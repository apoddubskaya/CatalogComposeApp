package com.example.catalog_compose.network.obj

import com.example.catalog_compose.data.UnsplashImageDetails
import com.example.catalog_compose.util.Transformable
import com.google.gson.annotations.SerializedName

data class UnsplashImageDetailsObj(
    @SerializedName("downloads") val downloads: Int?,
    @SerializedName("views") val views: Int?,
) : Transformable<UnsplashImageDetails> {

    override fun transform(): UnsplashImageDetails = UnsplashImageDetails(
        downloads = downloads ?: 0,
        views = views ?: 0,
    )
}