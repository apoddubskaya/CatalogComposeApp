package com.example.catalog_compose.network.util

interface Transformable<T> {
    fun transform(): T
}