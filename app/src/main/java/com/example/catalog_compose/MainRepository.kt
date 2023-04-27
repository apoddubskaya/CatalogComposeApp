package com.example.catalog_compose

import com.example.catalog_compose.data.UnsplashImage
import com.example.catalog_compose.network.MainNetwork
import com.example.catalog_compose.network.getNetworkService
import com.example.catalog_compose.util.transform

class MainRepository(private val service: MainNetwork = getNetworkService()) {

    suspend fun getImages(): List<UnsplashImage> = service.getImages().transform()
}