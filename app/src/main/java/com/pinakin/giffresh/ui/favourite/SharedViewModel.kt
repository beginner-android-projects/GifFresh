package com.pinakin.giffresh.ui.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    val isRefreshRequired = MutableLiveData<Boolean>()

    fun setRefreshRequired(required: Boolean) {

        isRefreshRequired.value = required
    }
}