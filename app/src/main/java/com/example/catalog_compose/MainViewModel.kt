package com.example.catalog_compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.catalog_compose.data.UnsplashImage
import com.example.catalog_compose.data.UnsplashImageDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    val images: Flow<PagingData<UnsplashImage>> = repository.getImages().cachedIn(viewModelScope)

    private val _selectedImage: MutableStateFlow<UnsplashImage?> = MutableStateFlow(null)
    val selectedImage: StateFlow<UnsplashImage?> = _selectedImage.asStateFlow()

    private val _selectedImageDetails: MutableStateFlow<UnsplashImageDetails?> = MutableStateFlow(null)
    val selectedImageDetails: StateFlow<UnsplashImageDetails?> = _selectedImageDetails.asStateFlow()

    fun selectImage(image: UnsplashImage) {
        _selectedImage.update { image }
        viewModelScope.launch {
            _selectedImageDetails.update { null }
            try {
                val details = repository.getImageDetails(image.id)
                _selectedImageDetails.update { details }
            } catch (e: Exception) {
                _selectedImageDetails.update { null }
            }
        }
    }
}