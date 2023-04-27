package com.example.catalog_compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalog_compose.data.UnsplashImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val ANOTHER_MOCK_IMAGE_URL =
    "https://images.pexels.com/photos/1459505/pexels-photo-1459505.jpeg?cs=srgb&dl=pexels-felix-mittermeier-1459505.jpg&fm=jpg"
private const val MOCK_IMAGE_URL =
    "https://images.unsplash.com/photo-1461988320302-91bde64fc8e4?ixid=2yJhcHBfaWQiOjEyMDd9&fm=jpg&fit=crop&w=1080&q=80&fit=max"

class MainViewModel : ViewModel() {

    private val _images = MutableStateFlow<List<UnsplashImage>>(listOf())
    val images: StateFlow<List<UnsplashImage>> = _images.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _images.value =
                MutableList(100) {
                    UnsplashImage(if (it % 3 == 0) MOCK_IMAGE_URL else ANOTHER_MOCK_IMAGE_URL)
                }.toList()
        }
    }
}