package com.pinakin.giffresh.repository

import com.pinakin.giffresh.data.remote.model.TrendingGif
import com.pinakin.giffresh.datasource.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class TrendingGifRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource
) {

    fun getTrendingGifs(page: Int = 0, size: Int = 20): Flow<TrendingGif> = flow {
        val response = networkDataSource.getTrendingGifs(page, size)
        emit(response)
    }
}