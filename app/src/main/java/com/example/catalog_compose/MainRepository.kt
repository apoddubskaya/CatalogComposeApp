package com.example.catalog_compose

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.catalog_compose.data.UnsplashImage
import com.example.catalog_compose.data.UnsplashImageDetails
import com.example.catalog_compose.network.MainNetwork
import com.example.catalog_compose.network.ServerConstants.IMAGES_PER_PAGE
import com.example.catalog_compose.network.pagination.ImagesPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val service: MainNetwork) {

    fun getImages(): Flow<PagingData<UnsplashImage>> = Pager(
        config = PagingConfig(pageSize = IMAGES_PER_PAGE),
        pagingSourceFactory = { ImagesPagingSource(service) }
    ).flow

    suspend fun getImageDetails(id: String): UnsplashImageDetails = service.getImageDetails(id).transform()
}