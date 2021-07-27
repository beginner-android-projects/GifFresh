package com.pinakin.giffresh.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pinakin.giffresh.data.local.converter.FavouriteGifJsonConverter
import com.pinakin.giffresh.data.local.dao.FavouriteGifDao
import com.pinakin.giffresh.data.local.entity.FavouriteGif

@Database(
    entities = [
        FavouriteGif::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(FavouriteGifJsonConverter::class)
abstract class GifFreshDataBase : RoomDatabase() {

    abstract fun getFavouriteGifDao(): FavouriteGifDao

}