package com.mayur.myimageapp.data.imageSearch

import com.mayur.myimageapp.data.AsyncResult

interface IImageRepository {
    suspend fun getSearchedImages(searchText: String, page: Int): AsyncResult<SearchResults>
}