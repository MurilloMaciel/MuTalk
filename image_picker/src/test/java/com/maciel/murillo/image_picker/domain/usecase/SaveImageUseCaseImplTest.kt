package com.maciel.murillo.image_picker.domain.usecase

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
class SaveImageUseCaseImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val imagePath = "imagePath"
    private val imageBytes = "imageBytes".toByteArray()
    private val repository: ImagePickerRepository = mockk()
    private val useCase: SaveImageUseCase = SaveImageUseCaseImpl(repository)

    @Test
    fun invoke_repositoryReturnError_returnSameError() = coroutineTestRule.runBlockingTest {
        val error = Result.Error(ImagePickerError.SaveImageIntoDb(EXCEPTION_MESSAGE))
        prepareScenario(saveImageResult = error)

        val result = repository.saveImage(imageBytes, ImagePathFactory.makeImagePath())

        assertEquals(error, result)
    }

    @Test
    fun invoke_repositoryReturnSuccess_returnImagePath() = coroutineTestRule.runBlockingTest {
        val resultSuccess = Result.Success(imagePath)
        prepareScenario(saveImageResult = resultSuccess)

        val result = repository.saveImage(imageBytes, ImagePathFactory.makeImagePath()).get()

        assertEquals(imagePath, result)
    }

    private fun prepareScenario(
        saveImageResult: Result<String, ImagePickerError> = Result.Success(imagePath)
    ) {
        coEvery { repository.saveImage(any(), any()) } returns saveImageResult
    }
}