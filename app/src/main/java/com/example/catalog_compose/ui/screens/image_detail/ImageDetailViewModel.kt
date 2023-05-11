package com.example.catalog_compose.ui.screens.image_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalog_compose.MainRepository
import com.example.catalog_compose.data.UnsplashImageDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: MainRepository,
) : ViewModel() {

    private val imageId: String = checkNotNull(savedStateHandle["imageId"])

    private val _imageDetailsUiState = MutableStateFlow<ImageDetailUiState>(ImageDetailUiState.Loading)
    val imageDetailsUiState: StateFlow<ImageDetailUiState> = _imageDetailsUiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val details = repository.getImageDetails(imageId)
                _imageDetailsUiState.update { ImageDetailUiState.Data(details) }
            } catch (e: Exception) {
                _imageDetailsUiState.update { ImageDetailUiState.Error }
            }
        }
    }
}

sealed interface ImageDetailUiState {
    object Loading : ImageDetailUiState
    data class Data(val details: UnsplashImageDetails) : ImageDetailUiState
    object Error : ImageDetailUiState
}