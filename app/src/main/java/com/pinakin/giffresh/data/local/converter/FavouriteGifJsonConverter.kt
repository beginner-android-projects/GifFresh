package com.pinakin.giffresh.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.pinakin.giffresh.data.remote.model.GifData

class FavouriteGifJsonConverter {

    @TypeConverter
    fun objToJsonString(data: GifData?): String? {

        return Gson().toJson(data).toString()

    }

    @TypeConverter
    fun jsonStringToObj(data: String?): GifData? {

        return Gson().fromJson(data, GifData::class.java)

    }
}