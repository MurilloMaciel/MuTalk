package com.maciel.murillo.image_picker.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciel.murillo.util.event.Event
import com.maciel.murillo.image_picker.domain.model.ImagePath
import com.maciel.murillo.image_picker.domain.usecase.SaveImageUseCaseImpl
import com.maciel.murillo.util.provider.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagePickerViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val saveImageUseCase: SaveImageUseCaseImpl
) : ViewModel() {

    private var imagePath: ImagePath? = null
    var pickType: PickType? = null

    private val _saveFinish = MutableLiveData<Event<String>>()
    val saveFinish: LiveData<Event<String>> = _saveFinish

    private val _saveError = MutableLiveData<Event<Unit>>()
    val saveError: LiveData<Event<Unit>> = _saveError

    fun onClickToPick(pickType: PickType, imagePath: ImagePath) {
        this.pickType = pickType
        this.imagePath = imagePath
    }

    private fun saveImage(imageBytes: ByteArray, imagePath: ImagePath) {
        viewModelScope.launch(dispatcherProvider.default()) {
            saveImageUseCase(imageBytes, imagePath).onError {
                _saveError.postValue(Event(Unit))
            }.onSuccess { imagePathOnDb ->
                _saveFinish.postValue(Event(imagePathOnDb))
            }
        }
    }

    fun onImagePrepared(imageBytes: ByteArray) {
        imagePath?.let { imagePath ->
            saveImage(imageBytes, imagePath)
        }
    }
}