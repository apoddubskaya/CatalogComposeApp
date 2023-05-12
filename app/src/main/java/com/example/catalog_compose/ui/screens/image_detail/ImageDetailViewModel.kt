package com.example.catalog_compose.ui.screens.image_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalog_compose.MainRepository
import com.example.catalog_compose.data.UnsplashImageDetails
import com.example.catalog_compose.ui.screens.image_detail.navigation.imageBlurHashArg
import com.example.catalog_compose.ui.screens.image_detail.navigation.imageIdArg
import com.example.catalog_compose.ui.util.Request
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

    private val imageId: String = checkNotNull(savedStateHandle[imageIdArg])
    private val imageBlurHash: String = checkNotNull(savedStateHandle[imageBlurHashArg])

    private val _imageDetailsUiState = MutableStateFlow(
        ImageDetailUiState(
            imageBlurHash = imageBlurHash,
            imageDetailsRequest = Request.Loading()
        )
    )
    val imageDetailsUiState: StateFlow<ImageDetailUiState> = _imageDetailsUiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val details = repository.getImageDetails(imageId)
                _imageDetailsUiState.update { it.copy(imageDetailsRequest = Request.Success(details)) }
            } catch (e: Exception) {
                _imageDetailsUiState.update { it.copy(imageDetailsRequest = Request.Error(e)) }
            }
        }
    }
}

data class ImageDetailUiState(
    val imageBlurHash: String,
    val imageDetailsRequest: Request<UnsplashImageDetails>,
)