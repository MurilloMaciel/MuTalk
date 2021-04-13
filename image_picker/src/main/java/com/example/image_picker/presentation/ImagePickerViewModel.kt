package com.example.image_picker.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.helper.Event
import com.example.image_picker.domain.model.ImageDestiny
import com.example.image_picker.domain.model.ImagePath
import com.example.image_picker.domain.usecase.SaveImageUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImagePickerViewModel(
    private val saveImageUseCase: SaveImageUseCase
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _saveError.postValue(Event(Unit))
    }

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

    fun onImagePrepared(imageBytes: ByteArray) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            imagePath?.run {
                saveImageUseCase(imageBytes, this)
                _saveFinish.postValue(Event(""))
            }
        }
    }
}