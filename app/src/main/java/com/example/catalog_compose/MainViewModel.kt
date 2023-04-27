package com.example.catalog_compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.catalog_compose.data.UnsplashImage
import kotlinx.coroutines.flow.Flow

class MainViewModel(repository: MainRepository = MainRepository()) : ViewModel() {

    val images: Flow<PagingData<UnsplashImage>> = repository.getImages().cachedIn(viewModelScope)
}