package com.pinakin.giffresh.di

import com.google.gson.GsonBuilder
import com.pinakin.giffresh.data.remote.GifFreshApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GifFreshApiModule {

    // Retrofit Logging Interceptor
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Ok HTTP client
    @Provides
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .hostnameVerifier { _, _ ->
                return@hostnameVerifier true
            }
            .build()

    // GifFreshApi client setup
    @Provides
    @Singleton
    fun provideGifFreshApi(client: OkHttpClient): GifFreshApi = Retrofit.Builder()
        .baseUrl("https://api.giphy.com/v1/gifs/")
        .client(client)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()
            )
        )
        .build().create(GifFreshApi::class.java)
}