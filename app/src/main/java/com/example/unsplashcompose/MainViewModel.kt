package com.example.unsplashcompose

import androidx.lifecycle.ViewModel
import com.example.unsplashcompose.data.UnsplashImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val MOCK_IMAGE_URL =
    "https://images.unsplash.com/photo-1461988320302-91bde64fc8e4?ixid=2yJhcHBfaWQiOjEyMDd9&fm=jpg&fit=crop&w=1080&q=80&fit=max"

class MainViewModel : ViewModel() {

    private val _imageToDisplay = MutableStateFlow(UnsplashImage(MOCK_IMAGE_URL))
    val imageToDisplay: StateFlow<UnsplashImage> = _imageToDisplay.asStateFlow()
}