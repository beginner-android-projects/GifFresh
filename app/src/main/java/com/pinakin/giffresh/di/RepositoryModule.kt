package com.pinakin.giffresh.di

import com.pinakin.giffresh.datasource.NetworkDataSource
import com.pinakin.giffresh.repository.TrendingGifRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTrendingGifRepository(
        networkDataSource: NetworkDataSource
    ) = TrendingGifRepository(networkDataSource)
}