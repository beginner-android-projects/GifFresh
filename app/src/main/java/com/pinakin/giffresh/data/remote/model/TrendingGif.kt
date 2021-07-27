package com.pinakin.giffresh.data.remote.model

data class TrendingGif(

    val data: List<GifData>,

    val pagination: Pagination,

    val meta: Meta

)
