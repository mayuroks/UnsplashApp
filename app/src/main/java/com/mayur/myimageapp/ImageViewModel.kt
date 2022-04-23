package com.mayur.myimageapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mayur.myimageapp.ResultState.*
import com.mayur.myimageapp.data.ImageRepository
import kotlinx.coroutines.launch

class ImageViewModel: ViewModel() {
    var imagesResponse by mutableStateOf<Any?>(null)
    private val imageRepository by lazy { ImageRepository() }


    fun getImages(str: String) {
        viewModelScope.launch {
            with(imageRepository.getSearchedImages(str)) {
                when {
                    isSuccess() -> {
                        imagesResponse = result
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