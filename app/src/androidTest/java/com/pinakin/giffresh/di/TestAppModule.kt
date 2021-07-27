package com.pinakin.giffresh.di

import android.content.Context
import androidx.room.Room
import com.pinakin.giffresh.data.local.GifFreshDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class TestAppModule {

    @Provides
    @Named("test_db")
    fun provideGifDataBase(@ApplicationContext context: Context) = Room.inMemoryDatabaseBuilder(
        context,
        GifFreshDataBase::class.java
    ).allowMainThreadQueries().build()
}