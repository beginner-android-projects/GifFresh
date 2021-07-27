package com.pinakin.giffresh.repository

import com.pinakin.giffresh.BaseTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class GifRepositoryTest : BaseTest() {


    @Inject
    @Named("testRepo")
    lateinit var gifRepository: GifRepository


    @Test
    fun insertAndGetGif() = runBlocking {

        gifRepository.saveGif(getGifData())

        val gif = gifRepository.favouriteGifs.first()[0]

        assertEquals(getGifData().id, gif.id)
    }
}