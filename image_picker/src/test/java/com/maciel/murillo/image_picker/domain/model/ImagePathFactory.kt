package com.maciel.murillo.image_picker.domain.model

object ImagePathFactory {

    fun makeImagePath(
        elementId: String = "elementId",
        imageDestiny: ImageDestiny = ImageDestiny.CHAT,
    ) = ImagePath(elementId, imageDestiny)
}