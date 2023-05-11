package com.example.catalog_compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.catalog_compose.data.UnsplashImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(repository: MainRepository) : ViewModel() {

    val images: Flow<PagingData<UnsplashImage>> = repository.getImages().cachedIn(viewModelScope)
}