package com.mayur.myimageapp.data.imageSearch

import com.mayur.myimageapp.data.AppApi
import com.mayur.myimageapp.data.AsyncResult
import com.mayur.myimageapp.data.apiCall

class ImageRepository : IImageRepository {

    override suspend fun getSearchedImages(searchText: String, page: Int): AsyncResult<SearchResults> {
        return apiCall { AppApi.imageService.getImagesForSearch(searchText, page) }
    }
}