package com.mayur.myimageapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mayur.myimageapp.data.imageSearch.ImageRepository

class ImageSearchViewModel: ViewModel() {
    private val imageRepository by lazy { ImageRepository() }
    val searchText = MutableLiveData("apple pie")

    var searchResults = Pager(PagingConfig(pageSize = 50)) {
        ImageSearchPagingSource(imageRepository, searchText.value)
    }.flow.cachedIn(viewModelScope)

    class Factory: ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ImageSearchViewModel() as T
        }
    }
}