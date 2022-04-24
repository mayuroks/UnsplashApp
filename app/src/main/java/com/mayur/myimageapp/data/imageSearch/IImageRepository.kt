package com.mayur.myimageapp.data.imageSearch

import com.mayur.myimageapp.AsyncResult
import com.mayur.myimageapp.data.SearchResults

interface IImageRepository {
    suspend fun getSearchedImages(searchText: String, page: Int): AsyncResult<SearchResults>
}