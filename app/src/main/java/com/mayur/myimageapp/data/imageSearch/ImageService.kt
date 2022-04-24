package com.mayur.myimageapp.data.imageSearch

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {

    // TODO get client id in retrofit
    // TODO get page from params
    @GET("search/photos/?client_id=9EMApcZtXbD3nZ-WEGtxjp_Ukd3FOitHNIpiDe_vWjk")
    suspend fun getImagesForSearch(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): Response<SearchResults>

}

