package com.example.catalog_compose.network.obj

import com.example.catalog_compose.data.UnsplashImage
import com.example.catalog_compose.util.Transformable
import com.google.gson.annotations.SerializedName

data class UnsplashImageObj(
    @SerializedName("id") val id: String,
    @SerializedName("blur_hash") val blurHash: String,
    @SerializedName("description") val description: String?,
    @SerializedName("urls") val urls: UnsplashImageUrlsObj,
) : Transformable<UnsplashImage> {

    override fun transform(): UnsplashImage =
        UnsplashImage(
            id = id,
            description = description.orEmpty(),
            url = urls.regular,
            blurHash = blurHash,
        )
}