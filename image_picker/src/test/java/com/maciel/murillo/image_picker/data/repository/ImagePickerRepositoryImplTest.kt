package com.maciel.murillo.image_picker.data.repository

import com.maciel.murillo.image_picker.data.datasource.ImagePickerRemoteDataSource
import com.maciel.murillo.image_picker.domain.model.ImagePathFactory
import com.maciel.murillo.image_picker.domain.model.ImagePickerError
import com.maciel.murillo.image_picker.domain.repository.ImagePickerRepository
import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.util.result.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

private const val EXCEPTION_MESSAGE = "EXCEPTION_MESSAGE"

@ExperimentalCoroutinesApi
class ImagePickerRepositoryImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val imagePath = "imagePath"
    private val imageBytes = "imageBytes".toByteArray()
    private val dataSource: ImagePickerRemoteDataSource = mockk()
    private val repository: ImagePickerRepository = ImagePickerRepositoryImpl(dataSource)

    @Test
    fun saveImage_dataSourceReturnError_returnSameError() = coroutineTestRule.runBlockingTest {
        val error = Result.Error(ImagePickerError.SaveImageIntoDb(EXCEPTION_MESSAGE))
        prepareScenario(saveImageResult = error)

        val result = repository.saveImage(imageBytes, ImagePathFactory.makeImagePath())

        assertEquals(error, result)
    }

    @Test
    fun saveImage_dataSourceReturnSuccess_returnImagePath() = coroutineTestRule.runBlockingTest {
        val resultSuccess = Result.Success(imagePath)
        prepareScenario(saveImageResult = resultSuccess)

        val result = repository.saveImage(imageBytes, ImagePathFactory.makeImagePath()).get()

        assertEquals(imagePath, result)
    }

    private fun prepareScenario(
        saveImageResult: Result<String, ImagePickerError> = Result.Success(imagePath)
    ) {
        coEvery { dataSource.saveImage(any(), any()) } returns saveImageResult
    }
}