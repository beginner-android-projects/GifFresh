package com.pinakin.giffresh.data.local.dao

import com.pinakin.giffresh.BaseTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

@HiltAndroidTest
class FavouriteGifDaoTest : BaseTest() {


    @Test
    fun insertAndGetFavouriteGif() = runBlocking {
        val favouriteGif = getFavouriteGifData()
        favouriteGifDao.insert(favouriteGif)

        val allFavouriteGif = favouriteGifDao.getFavouriteGifs().first()
        val actualGif = allFavouriteGif[0]
        assertEquals(favouriteGif.id, actualGif.id)
    }

    @Test
    fun givenGifInDatabaseShouldReturnTrueIfIdAreSame() = runBlocking {
        val favouriteGif = getFavouriteGifData()
        favouriteGifDao.insert(favouriteGif)

        val isMarkedAsFavourite = favouriteGifDao.isFavourite("testId")
        assertTrue(isMarkedAsFavourite)
    }

    @Test
    fun givenGifInDatabaseShouldReturnFalseIfIdAreNotSame() = runBlocking {
        val favouriteGif = getFavouriteGifData()
        favouriteGifDao.insert(favouriteGif)

        val isMarkedAsFavourite = favouriteGifDao.isFavourite("newId")
        assertFalse(isMarkedAsFavourite)
    }

    @Test
    fun deleteFavouriteGifShouldReturnNumberOfRowDeletedIfRecordDeleted() = runBlocking {
        val favouriteGif = getFavouriteGifData()
        favouriteGifDao.insert(favouriteGif)

        val rowDeleted = favouriteGifDao.delete(favouriteGif)
        assertTrue(rowDeleted > 0)
    }

    @Test
    fun deleteFavouriteGifShouldReturnZeroIfRecordNotDeleted() = runBlocking {
        val favouriteGif = getFavouriteGifData()
        favouriteGifDao.insert(favouriteGif)

        val rowDeleted = favouriteGifDao.delete(getFavouriteGifData("newId"))
        assertFalse(rowDeleted > 0)
    }


}