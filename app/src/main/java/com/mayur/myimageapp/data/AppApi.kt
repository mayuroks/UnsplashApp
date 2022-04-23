package com.mayur.myimageapp.data

object AppApi {
    val imageService by lazy { create<ImageService>() }
}