package com.example.catalog_compose.ui.util

sealed interface Request<T> {
    class Loading<T> : Request<T>
    data class Success<T>(val data: T) : Request<T>
    data class Error<T>(val error: Throwable?) : Request<T>
}