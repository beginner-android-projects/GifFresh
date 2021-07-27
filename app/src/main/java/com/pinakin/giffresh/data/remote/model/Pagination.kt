package com.pinakin.giffresh.data.remote.model

import com.google.gson.annotations.SerializedName

data class Pagination(

    @SerializedName("total_count")
    val totalPages: Int,

    @SerializedName("count")
    val size: Int,

    @SerializedName("offset")
    val currentPage: Int

)
