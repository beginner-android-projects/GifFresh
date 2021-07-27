package com.pinakin.giffresh.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pinakin.giffresh.data.remote.GifFreshApi
import com.pinakin.giffresh.data.remote.model.GifData
import com.pinakin.giffresh.utils.GifFreshException
import retrofit2.Response

class GifDataSource(
    private val api: GifFreshApi,
    private val query: String? = null,
    private val localDataSource: LocalDataSource
) : PagingSource<Int, GifData>() {


    override fun getRefreshKey(state: PagingState<Int, GifData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifData> {
        val pageIndex = params.key ?: 0
        return try {
            val response = if (query.isNullOrBlank()) {
                safeCall { api.getTrendingGifs(page = pageIndex) }
            } else {
                safeCall {
                    api.search(query = query, page = pageIndex)
                }
            }
            val nextKey = if (response != null) {
                pageIndex + 1
            } else {
                null
            }

            if (response != null && response.meta.status == 200) {

                response.data.forEach { gifData ->
                    gifData.isFavourite = localDataSource.isFavourite(gifData.id)
                }

                LoadResult.Page(
                    data = response.data,
                    prevKey = if (pageIndex == 0) null else pageIndex - 1,
                    nextKey = nextKey
                )
            } else {
                val meta = response?.meta
                LoadResult.Error(
                    GifFreshException(
                        1000,
                        "Status = ${meta?.status}, Message = ${meta?.msg}"
                    )
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(GifFreshException(1000, e.message))
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