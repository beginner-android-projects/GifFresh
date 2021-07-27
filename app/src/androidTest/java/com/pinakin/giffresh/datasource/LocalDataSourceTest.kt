package com.pinakin.giffresh.datasource

import com.pinakin.giffresh.BaseTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

@HiltAndroidTest
class LocalDataSourceTest : BaseTest() {

    private lateinit var dataSource: LocalDataSource

    override fun setUp() {
        super.setUp()
        dataSource = LocalDataSource(database)
    }

    @Test
    fun insertAndGetGif() = runBlocking {

        dataSource.insert(getGifData())

        val gifs = dataSource.getFavouriteGifs().first()
        val gif = gifs[0]
        assertEquals(getGifData().id, gif.id)
    }

    @Test
    fun givenGifInDatabaseShouldReturnTrueIfIdAreSame() = runBlocking {

        dataSource.insert(getGifData())

        val isMarkedAsFavourite = dataSource.isFavourite("testId")
        assertTrue(isMarkedAsFavourite)
    }

    @Test
    fun givenGifInDatabaseShouldReturnFalseIfIdAreNotSame() = runBlocking {

        dataSource.insert(getGifData())

        val isMarkedAsFavourite = dataSource.isFavourite("newId")
        assertFalse(isMarkedAsFavourite)
    }

    @Test
    fun deleteFavouriteGifShouldReturnNumberOfRowDeletedIfRecordDeleted() = runBlocking {

        dataSource.insert(getGifData())

        val rowDeleted = dataSource.deleteGif(getGifData())
        assertTrue(rowDeleted > 0)
    }

    @Test
    fun deleteFavouriteGifShouldReturnZeroIfRecordNotDeleted() = runBlocking {
        dataSource.insert(getGifData())

        val rowDeleted = dataSource.deleteGif(getGifData("newId"))
        assertFalse(rowDeleted > 0)
    }
}