package com.pinakin.giffresh.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pinakin.giffresh.data.remote.GifFreshApi
import com.pinakin.giffresh.data.remote.model.GifData
import com.pinakin.giffresh.datasource.GifDataSource
import com.pinakin.giffresh.datasource.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GifRepository @Inject constructor(
    private val api: GifFreshApi,
    private val localDataSource: LocalDataSource
) {

    val favouriteGifs = localDataSource.getFavouriteGifs()

    fun fetchGif(query: String?): Flow<PagingData<GifData>> = Pager(
        config = PagingConfig(
            pageSize = 1,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            GifDataSource(api, query, localDataSource)
        }
    ).flow

    suspend fun saveGif(gifData: GifData) = localDataSource.insert(gifData)

    suspend fun isFavourite(id: String) = localDataSource.isFavourite(id)

    suspend fun deleteGif(gifData: GifData) = localDataSource.deleteGif(gifData)
}