package com.example.catalog_compose.network.interceptor

import com.example.catalog_compose.BuildConfig
import com.example.catalog_compose.network.ServerConstants.HEADER_AUTH_KEY
import com.example.catalog_compose.network.ServerConstants.HEADER_AUTH_VALUE
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Adding access key header to requests
 */
class UnsplashAccessKeyInterceptor(
    private val accessKey: String = BuildConfig.accesskey
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val headersBuilder = originalRequest.headers.newBuilder()
        headersBuilder.add(
            HEADER_AUTH_KEY,
            String.format(HEADER_AUTH_VALUE, accessKey)
        )
        return chain.proceed(
            originalRequest.newBuilder().headers(headersBuilder.build()).build()
        )
    }
}