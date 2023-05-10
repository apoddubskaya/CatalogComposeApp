package com.example.catalog_compose.network.di

import com.example.catalog_compose.network.MainNetwork
import com.example.catalog_compose.network.ServerConstants
import com.example.catalog_compose.network.interceptor.UnsplashAccessKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(unsplashAccessKeyInterceptor: UnsplashAccessKeyInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(unsplashAccessKeyInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ServerConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): MainNetwork {
        return retrofit.create(MainNetwork::class.java)
    }
}