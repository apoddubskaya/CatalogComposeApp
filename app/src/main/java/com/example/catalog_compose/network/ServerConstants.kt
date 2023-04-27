package com.example.catalog_compose.network

object ServerConstants {

    const val HEADER_AUTH_KEY = "Authorization"
    const val HEADER_AUTH_VALUE = "Client-ID %s"

    const val BASE_URL = "https://api.unsplash.com/"

    const val IMAGES_PER_PAGE = 10
    const val IMAGES_URL = "photos?per_page=$IMAGES_PER_PAGE"
}