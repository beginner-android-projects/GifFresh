package com.pinakin.giffresh

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pinakin.giffresh.data.local.GifFreshDataBase
import com.pinakin.giffresh.data.local.dao.FavouriteGifDao
import com.pinakin.giffresh.data.local.entity.FavouriteGif
import com.pinakin.giffresh.data.remote.model.Downsized
import com.pinakin.giffresh.data.remote.model.GifData
import com.pinakin.giffresh.data.remote.model.Images
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
open class BaseTest {

    @get:Rule
    val hiltRule by lazy { HiltAndroidRule(this) }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: GifFreshDataBase

    lateinit var favouriteGifDao: FavouriteGifDao

    @Before
    open fun setUp() {

        hiltRule.inject()

        favouriteGifDao = database.getFavouriteGifDao()

    }

    @After
    fun tearDown() {

        database.close()

    }

    fun getFavouriteGifData(id: String = "testId"): FavouriteGif {

        return FavouriteGif(id, getGifData(id))
    }

    fun getGifData(id: String = "testId"): GifData {

        val downloadSize = Downsized(100, 100, 100, "https://pinakin.dev")

        val images = Images(downloadSize, downloadSize, downloadSize, downloadSize, downloadSize)

        return GifData(id, images, true)

    }
}