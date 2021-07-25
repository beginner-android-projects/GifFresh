package com.pinakin.giffresh.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pinakin.giffresh.data.remote.model.GifData
import com.pinakin.giffresh.datasource.GifDataSource
import com.pinakin.giffresh.datasource.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GifRepository @Inject constructor(
    private val dataSource: GifDataSource,
    private val localDataSource: LocalDataSource
) {

    val favouriteGifs = localDataSource.getFavouriteGifs()

    fun fetchGif(query: String?): Flow<PagingData<GifData>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            dataSource.query = query
            dataSource
        }
    ).flow

    suspend fun saveGif(gifData: GifData) = localDataSource.insert(gifData)

    suspend fun isFavourite(id: String) = localDataSource.isFavourite(id)
}