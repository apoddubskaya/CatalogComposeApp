package com.example.catalog_compose.network.obj

import com.example.catalog_compose.data.UnsplashImage
import com.example.catalog_compose.network.util.Transformable
import com.google.gson.annotations.SerializedName

data class UnsplashImageObj(
    @SerializedName("id") val id: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("blur_hash") val blurHash: String,
    @SerializedName("alt_description") val altDescription: String?,
    @SerializedName("urls") val urls: UnsplashImageUrlsObj,
) : Transformable<UnsplashImage> {

    override fun transform(): UnsplashImage =
        UnsplashImage(
            id = id,
            altDescription = altDescription.orEmpty(),
            url = urls.regular,
            blurHash = blurHash,
            width = width,
            height = height,
        )
}