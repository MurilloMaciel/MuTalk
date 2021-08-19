package com.maciel.murillo.image_picker.domain.usecase

import com.maciel.murillo.image_picker.domain.repository.ImagePickerRepository
import io.mockk.mockk
import org.junit.Test

class SaveImageUseCaseImplTest {

    private val repository: ImagePickerRepository = mockk()
    private val useCase: SaveImageUseCase = SaveImageUseCaseImpl(repository)

    @Test
    fun invoke_callRepository() {

    }
}