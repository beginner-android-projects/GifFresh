package com.pinakin.giffresh.di

import android.content.Context
import androidx.room.Room
import com.pinakin.giffresh.data.local.GifFreshDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GifFreshDatabaseModule {

    @Provides
    @Singleton
    fun providesGifFreshDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        GifFreshDataBase::class.java,
        "gif_fresh_db"
    ).build()

}