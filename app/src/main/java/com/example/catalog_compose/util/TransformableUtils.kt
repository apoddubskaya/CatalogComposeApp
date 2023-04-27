package com.example.catalog_compose.util

fun <T, E : Transformable<T>> Iterable<E>.transform(): List<T> = map { it.transform() }