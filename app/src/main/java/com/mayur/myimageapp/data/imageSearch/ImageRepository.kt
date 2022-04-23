package com.mayur.myimageapp.data.imageSearch

import com.mayur.myimageapp.AsyncResult
import com.mayur.myimageapp.data.AppApi
import com.mayur.myimageapp.data.SearchResults
import com.mayur.myimageapp.data.apiCall

class ImageRepository {

    suspend fun getSearchedImages(str: String): AsyncResult<SearchResults> {
        return apiCall { AppApi.imageService.getImagesForSearch(str) }
    }
}