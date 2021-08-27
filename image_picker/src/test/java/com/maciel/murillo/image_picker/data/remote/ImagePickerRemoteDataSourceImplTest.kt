package com.maciel.murillo.image_picker.data.remote

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.maciel.murillo.image_picker.data.datasource.ImagePickerRemoteDataSource
import com.maciel.murillo.image_picker.domain.model.ImagePathFactory
import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.util.result.Result
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class ImagePickerRemoteDataSourceImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val imageBytes = "imageBytes".toByteArray()
    private val storage: StorageReference = mockk()
    private val uploadTask: UploadTask = mockk()
    private val uri: Uri = mockk(relaxed = true)
    private val task: Task<Uri> = mockk()
    private val uploadTaskSnapshot: UploadTask.TaskSnapshot = mockk()
    private val dataSource: ImagePickerRemoteDataSource = ImagePickerRemoteDataSourceImpl(
        storage = storage
    )

    @Test
    fun saveImage_exceptionCaught_returnsError() = coroutineTestRule.runBlockingTest {
        prepareScenario(throwException = true)

        val result = dataSource.saveImage(imageBytes, ImagePathFactory.makeImagePath())

        assertTrue(result is Result.Error)
    }

    @Test
    fun saveImage_pathEmpty_returnsError() = coroutineTestRule.runBlockingTest {
        prepareUriScenario(empty = true)
        prepareScenario(imagePathMock = "")

        val result = dataSource.saveImage(imageBytes, ImagePathFactory.makeImagePath())

        assertTrue(result is Result.Error)
        finishUriScenario()
    }

    @Test
    fun saveImage_validPath_returnPath() = coroutineTestRule.runBlockingTest {
        val imagePathMock = "https://google.com.br/"
        prepareUriScenario(imagePathMock = imagePathMock, empty = false)
        prepareScenario(imagePathMock = imagePathMock)

        val result = dataSource.saveImage(imageBytes, ImagePathFactory.makeImagePath())

        assertTrue(result is Result.Success)
        finishUriScenario()
    }

    fun prepareUriScenario(imagePathMock: String = "imagePathMock", empty: Boolean = false) {
        clearAllMocks()
        mockkStatic(Uri::parse)
        if (empty) {
            every { Uri.parse(any()) } returns Uri.EMPTY
        } else {
            every { Uri.parse(any()) } returns uri
            every { uri.toString() } returns imagePathMock
        }
    }

    fun finishUriScenario() {
        unmockkStatic(Uri::parse)
    }

    private fun prepareScenario(
        throwException: Boolean = false,
        imagePathMock: String = "imagePathMock"
    ) {
        if (throwException) {
            coEvery { storage.child(any()) } throws Exception()
        } else {
            coEvery { storage.child(any()) } returns storage
            coEvery { storage.putBytes(any()) } returns uploadTask
            coEvery { uploadTask.exception } returns null
            coEvery { uploadTask.isCanceled } returns false
            coEvery { uploadTask.isComplete } returns true
            coEvery { uploadTask.result } returns uploadTaskSnapshot
            coEvery { uploadTaskSnapshot.storage } returns storage
            coEvery { storage.downloadUrl } returns task
            coEvery { task.exception } returns null
            coEvery { task.isCanceled } returns false
            coEvery { task.isComplete } returns true
            coEvery { task.result } returns Uri.parse(imagePathMock)
        }
    }
}