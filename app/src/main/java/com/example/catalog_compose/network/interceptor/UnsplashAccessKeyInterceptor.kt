package com.example.catalog_compose.network.interceptor

import com.example.catalog_compose.BuildConfig
import com.example.catalog_compose.network.ServerConstants.HEADER_AUTH_KEY
import com.example.catalog_compose.network.ServerConstants.HEADER_AUTH_VALUE
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Adding access key header to requests
 */
class UnsplashAccessKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val headersBuilder = originalRequest.headers.newBuilder()
        headersBuilder.add(
            HEADER_AUTH_KEY,
            String.format(HEADER_AUTH_VALUE, BuildConfig.accesskey)
        )
        return chain.proceed(
            originalRequest.newBuilder().headers(headersBuilder.build()).build()
        )
    }
}