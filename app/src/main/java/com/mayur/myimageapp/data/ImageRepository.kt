package com.mayur.myimageapp.data

import com.mayur.myimageapp.AsyncResult

class ImageRepository {

    suspend fun getSearchedImages(str: String): AsyncResult<Any> {
        return apiCall { AppApi.imageService.getImagesForSearch(str) }
    }
}