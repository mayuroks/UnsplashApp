package com.mayur.myimageapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mayur.myimageapp.data.SearchResults
import com.mayur.myimageapp.data.imageSearch.ImageRepository
import kotlinx.coroutines.launch

class ImageViewModel: ViewModel() {
    var imagesResponse = mutableStateOf<SearchResults?>(null)
    private val imageRepository by lazy { ImageRepository() }


    fun getImages(str: String) {
        viewModelScope.launch {
            with(imageRepository.getSearchedImages(str)) {
                when {
                    isSuccess() -> {
                        imagesResponse.value = result
                    }
                    inProgress() -> {}
                    isError() -> {}
                }
            }
        }
    }

    class Factory: ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ImageViewModel() as T
        }
    }
}