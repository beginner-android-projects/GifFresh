package com.pinakin.giffresh.ui.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.pinakin.giffresh.data.remote.model.GifData
import com.pinakin.giffresh.repository.GifRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(
    private val gifRepository: GifRepository
) : ViewModel() {

    private val _gifs = MutableStateFlow<PagingData<GifData>>(PagingData.empty())

    val gifs: SharedFlow<PagingData<GifData>> = _gifs.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = PagingData.empty()
    )

    fun fetchGifs(query: String? = null) = viewModelScope.launch {

        gifRepository.fetchGif(query).collectLatest {

            _gifs.value = it

        }
    }

    fun saveGif(gifData: GifData) = viewModelScope.launch {

        withContext(Dispatchers.IO) {

            gifRepository.saveGif(gifData)

        }

    }

    fun delete(gifData: GifData) = viewModelScope.launch {

        gifRepository.deleteGif(gifData)

    }
}