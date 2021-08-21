package com.maciel.murillo.image_picker.data.remote

import com.google.firebase.storage.StorageReference
import com.maciel.murillo.image_picker.data.datasource.ImagePickerRemoteDataSource
import com.maciel.murillo.image_picker.domain.model.ImagePathFactory
import com.maciel.murillo.image_picker.domain.model.ImagePickerError
import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.util.result.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

private const val EXCEPTION_MESSAGE = "EXCEPTION_MESSAGE"

@ExperimentalCoroutinesApi
class ImagePickerRemoteDataSourceImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val imageBytes = "imageBytes".toByteArray()
    private val storage: StorageReference = mockk()
    private val dataSource: ImagePickerRemoteDataSource = ImagePickerRemoteDataSourceImpl(
        storage = storage
    )

    @Test
    fun saveImage_exceptionCaught_returnsError() = coroutineTestRule.runBlockingTest {
        prepareScenario(throwException = true)

        val result = dataSource.saveImage(imageBytes, ImagePathFactory.makeImagePath())

        val expected = Result.Error(ImagePickerError.SaveImageIntoDb(EXCEPTION_MESSAGE))
        assertEquals(expected, result)
    }

    @Test
    fun saveImage_pathEmpty_returnsError() = coroutineTestRule.runBlockingTest {
        prepareScenario(imagePathMock = "")

        val result = dataSource.saveImage(imageBytes, ImagePathFactory.makeImagePath())

        val expected = Result.Error(ImagePickerError.SaveImageIntoDb(IMAGE_PATH_ERROR_MESSAGE))
        assertEquals(expected, result)
    }

    @Test
    fun saveImage_validPath_returnPath() = coroutineTestRule.runBlockingTest {
        val imagePathMock = "imagePathMock"
        prepareScenario(imagePathMock = imagePathMock)

        val result = dataSource.saveImage(imageBytes, ImagePathFactory.makeImagePath()).get()

        assertEquals(imagePathMock, result)
    }

    private fun prepareScenario(
        throwException: Boolean = false,
        imagePathMock: String = "imagePathMock"
    ) {
        if (throwException) {
            coEvery {
                storage.child(any())
                    .child(any())
                    .child(any())
                    .putBytes(any())
                    .await()
                    .storage
                    .downloadUrl
                    .await()
                    ?.toString()
            } throws Exception(EXCEPTION_MESSAGE)
        } else {
            coEvery {
                storage.child(any())
                    .child(any())
                    .child(any())
                    .putBytes(any())
                    .await()
                    .storage
                    .downloadUrl
                    .await()
                    ?.toString()
            } returns imagePathMock
        }
    }
}