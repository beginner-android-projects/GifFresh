package com.pinakin.giffresh.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinakin.giffresh.repository.GifRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FavouriteGifViewModel @Inject constructor(
    repository: GifRepository
) : ViewModel() {

    val favouriteGifs = repository.favouriteGifs.map { favGifs ->
        return@map favGifs.map {
            it.data
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}