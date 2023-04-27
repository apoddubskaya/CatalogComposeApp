package com.example.catalog_compose.network

import com.example.catalog_compose.util.UnsplashAccessKeyInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://api.unsplash.com/"

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
    @GET("photos")
    suspend fun getImages(): List<UnsplashImageObj>
}