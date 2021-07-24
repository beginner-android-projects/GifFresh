package com.pinakin.giffresh.utils

class GifFreshException(
    val errCode: Int,
    message: String? = null
) : Exception(message)