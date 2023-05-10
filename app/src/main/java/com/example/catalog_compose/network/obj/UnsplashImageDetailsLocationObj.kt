package com.example.catalog_compose.network.obj

import com.example.catalog_compose.util.Transformable
import com.google.gson.annotations.SerializedName

data class UnsplashImageDetailsLocationObj(
    @SerializedName("name") val name: String?
) : Transformable<String> {

    override fun transform(): String = name.orEmpty()
}