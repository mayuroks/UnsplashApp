package com.mayur.myimageapp

import com.mayur.myimageapp.data.SearchResults
import com.mayur.myimageapp.data.imageSearch.IImageRepository

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