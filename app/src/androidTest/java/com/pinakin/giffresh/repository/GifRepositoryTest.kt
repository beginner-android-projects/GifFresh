package com.pinakin.giffresh.repository

import com.pinakin.giffresh.BaseTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
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

    @Test
    fun givenGifInDatabaseShouldReturnTrueIfIdAreSame() = runBlocking {

        gifRepository.saveGif(getGifData())

        val isMarkedAsFavourite = gifRepository.isFavourite("testId")

        assertTrue(isMarkedAsFavourite)

    }

    @Test
    fun givenGifInDatabaseShouldReturnFalseIfIdAreNotSame() = runBlocking {

        gifRepository.saveGif(getGifData())

        val isMarkedAsFavourite = gifRepository.isFavourite("newId")

        assertFalse(isMarkedAsFavourite)

    }

    @Test
    fun deleteFavouriteGifShouldReturnNumberOfRowDeletedIfRecordDeleted() = runBlocking {

        gifRepository.saveGif(getGifData())

        val rowDeleted = gifRepository.deleteGif(getGifData())

        assertTrue(rowDeleted > 0)

    }

    @Test
    fun deleteFavouriteGifShouldReturnZeroIfRecordNotDeleted() = runBlocking {

        gifRepository.saveGif(getGifData())

        val rowDeleted = gifRepository.deleteGif(getGifData("newId"))

        assertFalse(rowDeleted > 0)

    }
}