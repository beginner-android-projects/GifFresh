package com.pinakin.giffresh.data.local.dao

import androidx.room.*
import com.pinakin.giffresh.data.local.entity.FavouriteGif
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteGifDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favouriteGif: FavouriteGif)

    @Delete
    suspend fun delete(favouriteGif: FavouriteGif)

    @Query("SELECT * FROM fav_gif")
    fun getFavouriteGifs(): Flow<List<FavouriteGif>>

    @Query("SELECT id FROM fav_gif where id LIMIT :id")
    fun getAllFavouriteGifsId(id: String): Flow<List<String>>
}