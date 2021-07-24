package com.pinakin.giffresh.di

import com.pinakin.giffresh.data.remote.GifFreshApi
import com.pinakin.giffresh.datasource.NetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideNetworkDataSource(api: GifFreshApi) = NetworkDataSource(api)
}