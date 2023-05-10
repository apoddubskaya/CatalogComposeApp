package com.example.catalog_compose.network.obj

import com.example.catalog_compose.data.UnsplashImageDetails
import com.example.catalog_compose.network.util.Transformable
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class UnsplashImageDetailsObj(
    @SerializedName("created_at") val created: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("location") val location: UnsplashImageDetailsLocationObj?,
    @SerializedName("downloads") val downloads: Int?,
    @SerializedName("views") val views: Int?,
) : Transformable<UnsplashImageDetails> {

    override fun transform(): UnsplashImageDetails = UnsplashImageDetails(
        created = created?.let { LocalDateTime.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")) },
        description = description.orEmpty(),
        location = location?.transform().orEmpty(),
        downloads = downloads ?: 0,
        views = views ?: 0,
    )
}