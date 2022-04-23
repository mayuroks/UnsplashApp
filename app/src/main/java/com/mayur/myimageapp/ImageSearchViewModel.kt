package com.mayur.myimageapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mayur.myimageapp.data.SearchResultItem
import com.mayur.myimageapp.data.imageSearch.ImageRepository
import kotlinx.coroutines.launch

class ImageSearchViewModel: ViewModel() {
    private val imageRepository by lazy { ImageRepository() }
    val searchText = mutableStateOf("apple pie")
    val searchResults = mutableStateOf<List<SearchResultItem>>(emptyList())

//    val imageSearchPagingSource = ImageSearchPagingSource(imageRepository, searchText.value)
//    val pagingConfig = PagingConfig(pageSize = 50)
//    var searchResults = Pager(pagingConfig) {
//        imageSearchPagingSource
//    }.flow.cachedIn(viewModelScope)

    fun getSearchedImages() {
        viewModelScope?.launch {
            val response = imageRepository.getSearchedImages(searchText = searchText.value, page = 1)

            when {
                response.isSuccess() -> {
                    response.result?.results?.let { searchResults.value = it }
                }
                response.isError() -> {
                    // TODO
                }
                response.inProgress() -> {
                    // TODO
                }
            }


//            TODO non-feasible code
//            val result = imageSearchPagingSource.load(
//                PagingSource.LoadParams.Refresh(
//                    1,
//                    50,
//                    false,
//                )
//            )
//
//            when(result) {
//                is PagingSource.LoadResult.Page -> {
//                    result.data
//                }
//                is PagingSource.LoadResult.Error -> TODO()
//                is PagingSource.LoadResult.Invalid -> TODO()
//                is PagingSource.LoadResult.Page -> TODO()
//            }
        }
    }


    class Factory: ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ImageSearchViewModel() as T
        }
    }
}