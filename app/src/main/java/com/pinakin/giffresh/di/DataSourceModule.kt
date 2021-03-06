package com.pinakin.giffresh.di

import com.pinakin.giffresh.data.local.GifFreshDataBase
import com.pinakin.giffresh.datasource.LocalDataSource
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
    fun provideLocalDataSource(database: GifFreshDataBase) = LocalDataSource(database)

}