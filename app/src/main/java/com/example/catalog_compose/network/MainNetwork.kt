package com.example.catalog_compose.network

import com.example.catalog_compose.network.ServerConstants.BASE_URL
import com.example.catalog_compose.network.ServerConstants.IMAGES_URL
import com.example.catalog_compose.network.interceptor.UnsplashAccessKeyInterceptor
import com.example.catalog_compose.network.obj.UnsplashImageObj
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


private val service: MainNetwork by lazy {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(UnsplashAccessKeyInterceptor())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    retrofit.create(MainNetwork::class.java)
}

fun getNetworkService() = service

interface MainNetwork {

    @Headers("Accept-Version: v1")
    @GET(IMAGES_URL)
    suspend fun getImages(@Query("page") page: Int = 1): List<UnsplashImageObj>
}