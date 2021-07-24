package com.pinakin.giffresh.ui.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinakin.giffresh.data.remote.model.TrendingGif
import com.pinakin.giffresh.repository.TrendingGifRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingGifViewModel @Inject constructor(
    private val repository: TrendingGifRepository
) : ViewModel() {

    private val _trendingGifs = MutableSharedFlow<TrendingGif>()
    val trendingGifs: SharedFlow<TrendingGif> = _trendingGifs.shareIn(
        scope = viewModelScope,
        replay = 0,
        started = SharingStarted.WhileSubscribed(500)
    )


    fun getTrendingGifs(page: Int = 0, size: Int = 20) = viewModelScope.launch {
        repository.getTrendingGifs(page, size)
            .catch { error ->

            }
            .collect {
                _trendingGifs.emit(it)
            }
    }

    fun search(query: String, page: Int = 0, size: Int = 20) = viewModelScope.launch {
        repository.search(query, page, size)
            .catch { error ->

            }
            .collect {
                _trendingGifs.emit(it)
            }
    }
}