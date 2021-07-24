package com.pinakin.giffresh.data.remote.model

import com.google.gson.annotations.SerializedName

data class Images(
    val downsized: Downsized,
    @SerializedName("downsized_large")
    val downsizedLarge: Downsized,
    @SerializedName("downsized_medium")
    val downsizedMedium: Downsized,
    @SerializedName("downsized_small")
    val downsizedSmall: Downsized,
    @SerializedName("downsized_still")
    val downsizedStill: Downsized
)
