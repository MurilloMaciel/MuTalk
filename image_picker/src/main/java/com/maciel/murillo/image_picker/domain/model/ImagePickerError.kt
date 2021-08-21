package com.maciel.murillo.image_picker.domain.model

sealed class ImagePickerError(val message: String) {
    class SaveImageIntoDb(message: String) : ImagePickerError(message)
}