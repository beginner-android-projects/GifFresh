package com.pinakin.giffresh.di

import com.pinakin.giffresh.datasource.GifDataSource
import com.pinakin.giffresh.repository.GifRepository
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
    fun provideGifRepository(
        gifDataSource: GifDataSource
    ) = GifRepository(gifDataSource)
}