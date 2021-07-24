package com.pinakin.giffresh.ui.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class TrendingGifViewModel @Inject constructor() : ViewModel() {

    val trendingGifs: SharedFlow<List<String>> = flow<List<String>> {
        val gifs = listOf(
            "https://media.giphy.com/media/l4FGBYUAKXu5rCN9K/giphy.gif",
            "https://media.giphy.com/media/3ov9jVsyMdxWpBU5z2/giphy.gif",
            "https://media.giphy.com/media/xUA7b4cBBELoFPeUsU/giphy.gif",
            "https://media.giphy.com/media/dapSl72ddH5gQ/giphy.gif"
        )
        emit(gifs)
    }.shareIn(
        scope = viewModelScope,
        replay = 0,
        started = SharingStarted.WhileSubscribed(500)
    )
}