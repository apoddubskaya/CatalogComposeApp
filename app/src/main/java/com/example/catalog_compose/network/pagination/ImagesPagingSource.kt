package com.example.catalog_compose.network.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.catalog_compose.data.UnsplashImage
import com.example.catalog_compose.network.MainNetwork
import com.example.catalog_compose.network.util.transform

class ImagesPagingSource(
    private val service: MainNetwork
) : PagingSource<Int, UnsplashImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> =
        try {
            val page = params.key ?: 1
            val images = service.getImages(page = page).transform()
            LoadResult.Page(
                data = images,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (images.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }

}