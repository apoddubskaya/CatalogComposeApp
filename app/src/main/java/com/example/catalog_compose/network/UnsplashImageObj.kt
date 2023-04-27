package com.example.catalog_compose.network

import com.example.catalog_compose.data.UnsplashImage
import com.example.catalog_compose.util.Transformable
import com.google.gson.annotations.SerializedName

data class UnsplashImageObj(
    @SerializedName("id") val id: String,
    @SerializedName("description") val description: String?,
    @SerializedName("urls") val urls: UnsplashImageUrlsObj,
) : Transformable<UnsplashImage> {

    override fun transform(): UnsplashImage =
        UnsplashImage(
            id = id,
            description = description.orEmpty(),
            url = urls.regular
        )
}