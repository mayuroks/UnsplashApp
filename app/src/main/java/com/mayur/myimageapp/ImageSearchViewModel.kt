package com.mayur.myimageapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.mayur.myimageapp.data.SearchResults
import com.mayur.myimageapp.data.imageSearch.ImageRepository
import kotlinx.coroutines.launch

class ImageSearchViewModel: ViewModel() {
    private val imageRepository by lazy { ImageRepository() }
    var palette = mutableStateOf<Palette?>(null)
    val searchText = mutableStateOf("")
    val searchResults = mutableStateOf<SearchResults?>(null)
    val searchError = mutableStateOf<Throwable?>(null)
    val showErrorUi = MutableLiveData(false)
    val showErrorToast = MutableLiveData(false)

    fun getSearchedImages() {
        if (searchText.value.isBlank()) return

        viewModelScope?.launch {
            resetErrorData()

            val response = imageRepository.getSearchedImages(searchText = searchText.value, page = 1)

            when {
                response.isSuccess() -> {
                    response.result?.let {
                        searchResults.value = it
                        searchError.value = null
                    }
                }
                response.isError() -> {
                    searchError.value = response.error
                    if (searchResults.value == null) {
                        // show Error screen
                        showErrorUi.value = true
                    } else {
                        // show toast message
                        showErrorToast.value = true
                    }

                }
                response.inProgress() -> {
                    // TODO
                }
            }
        }
    }

    private fun resetErrorData() {
        searchError.value = null
        showErrorUi.value = false
        showErrorToast.value = false
    }


    class Factory: ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ImageSearchViewModel() as T
        }
    }
}