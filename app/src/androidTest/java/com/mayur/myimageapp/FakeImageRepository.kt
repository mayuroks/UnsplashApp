package com.mayur.myimageapp

import com.mayur.myimageapp.data.AsyncResult
import com.mayur.myimageapp.data.imageSearch.IImageRepository
import com.mayur.myimageapp.data.imageSearch.SearchResults

class FakeImageRepository: IImageRepository {

    private var simulatedResponse: AsyncResult<Any?>? = null

    override suspend fun getSearchedImages(
        searchText: String,
        page: Int
    ): AsyncResult<SearchResults> {
        return simulatedResponse as AsyncResult<SearchResults>
    }

    fun setResponse(response: AsyncResult<Any?>?) {
        simulatedResponse = response
    }
}