package com.example.catalog_compose

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalog_compose.data.UnsplashImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(repository: MainRepository = MainRepository()) : ViewModel() {

    private val _images = MutableStateFlow<List<UnsplashImage>>(listOf())
    val images: StateFlow<List<UnsplashImage>> = _images.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _images.value = repository.getImages()
            } catch (e: Throwable) {
                Log.d("Get unsplash data", "Error occurred: ${e.message}")
            }
        }
    }
}