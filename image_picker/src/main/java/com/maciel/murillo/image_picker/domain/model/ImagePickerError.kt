package com.maciel.murillo.image_picker.domain.model

sealed class ImagePickerError {
    object SaveImageIntoDb : ImagePickerError()
}