package com.pinakin.giffresh.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pinakin.giffresh.data.remote.model.GifData

@Entity(tableName = "fav_gif")
data class FavouriteGif(
    @PrimaryKey
    val id: String,
    val data: GifData
)
