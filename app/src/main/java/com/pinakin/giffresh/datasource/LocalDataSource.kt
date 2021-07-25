package com.pinakin.giffresh.datasource

import com.pinakin.giffresh.data.local.GifFreshDataBase
import com.pinakin.giffresh.data.local.entity.FavouriteGif
import com.pinakin.giffresh.data.remote.model.GifData
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dataBase: GifFreshDataBase
) {

    suspend fun insert(gifData: GifData) = dataBase.getFavouriteGifDao().insert(
        FavouriteGif(gifData.id, gifData)
    )

    fun getFavouriteGifs() = dataBase.getFavouriteGifDao().getFavouriteGifs()
}