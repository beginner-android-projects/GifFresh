package com.pinakin.giffresh.datasource

import com.pinakin.giffresh.data.remote.GifFreshApi
import com.pinakin.giffresh.data.remote.model.TrendingGif
import com.pinakin.giffresh.utils.GifFreshException
import retrofit2.Response
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val api: GifFreshApi
) {


    suspend fun getTrendingGifs(page: Int, size: Int = 20): TrendingGif {
        val response = safeCall {
            api.getTrendingGifs(page = page, size = size)
        }

        if (response != null && response.meta.status == 200) {
            return response
        } else {
            throw GifFreshException(
                1000,
                "ID = ${response?.meta?.responseId} Status = ${response?.meta?.status} Message = ${response?.meta?.msg}"
            )
        }
    }

    suspend fun search(query: String, page: Int, size: Int = 20): TrendingGif{
        val response = safeCall {
            api.search(query = query,page = page,size = size)
        }

        if (response != null && response.meta.status == 200) {
            return response
        } else {
            throw GifFreshException(
                1000,
                "ID = ${response?.meta?.responseId} Status = ${response?.meta?.status} Message = ${response?.meta?.msg}"
            )
        }
    }

    private suspend inline fun <T> safeCall(crossinline responseFun: suspend () -> Response<T>): T? {
        return try {
            val result = responseFun.invoke()
            if (result.isSuccessful) {
                result.body()
            } else {
                throw GifFreshException(1000, result.errorBody()?.string())
            }
        } catch (e: Exception) {
            throw GifFreshException(1000, e.message)
        }
    }
}