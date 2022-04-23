package com.mayur.myimageapp.data

import com.mayur.myimageapp.data.imageSearch.ImageService

object AppApi {
    val imageService by lazy { create<ImageService>() }
}