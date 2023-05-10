package com.example.catalog_compose.network.util

fun <T, E : Transformable<T>> Iterable<E>.transform(): List<T> = map { it.transform() }