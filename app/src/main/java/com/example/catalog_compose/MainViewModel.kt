package com.example.catalog_compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.catalog_compose.data.UnsplashImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(repository: MainRepository = MainRepository()) : ViewModel() {

    val images: Flow<PagingData<UnsplashImage>> = repository.getImages().cachedIn(viewModelScope)

    private val _selectedImage: MutableStateFlow<UnsplashImage?> = MutableStateFlow(null)
    val selectedImage: StateFlow<UnsplashImage?> = _selectedImage.asStateFlow()

    fun selectImage(image: UnsplashImage) {
        _selectedImage.update { image }
    }
}