package com.maciel.murillo.gif_finder.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciel.murillo.gif_finder.domain.model.GetGifsError
import com.maciel.murillo.gif_finder.domain.model.Gif
import com.maciel.murillo.gif_finder.domain.usecase.GetTrendingGifsUseCase
import com.maciel.murillo.gif_finder.domain.usecase.SearchGifsUseCase
import com.maciel.murillo.util.provider.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val QUERY_MIN_LENGTH = 3

@HiltViewModel
class GifFinderViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getTrendingGifsUseCase: GetTrendingGifsUseCase,
    private val searchGifsUseCase: SearchGifsUseCase
) : ViewModel() {

    private val _error = MutableLiveData<GetGifsError?>()
    val error: LiveData<GetGifsError?> = _error

    private val _trendingGifs = MutableLiveData<List<Gif>>()
    val trendingGifs: LiveData<List<Gif>> = _trendingGifs

    private val _fetchedGifs = MutableLiveData<List<Gif>>()
    val fetchedGifs: LiveData<List<Gif>> = _fetchedGifs

    private fun canSearchGifs(query: String) = query.length >= QUERY_MIN_LENGTH

    fun getTrendingGifs() {
        viewModelScope.launch(dispatcherProvider.io()) {
            getTrendingGifsUseCase().onError { error ->
                _error.postValue(error)
            }.onSuccess { trendingGifs ->
                _error.postValue(null)
                _trendingGifs.postValue(trendingGifs)
            }
        }
    }

    fun searchGifs(query: String) {
        if (canSearchGifs(query)) {
            viewModelScope.launch(dispatcherProvider.io()) {
                searchGifsUseCase(query).onError { error ->
                    _error.postValue(error)
                }.onSuccess { trendingGifs ->
                    _error.postValue(null)
                    _fetchedGifs.postValue(trendingGifs)
                }
            }
        }
    }
}