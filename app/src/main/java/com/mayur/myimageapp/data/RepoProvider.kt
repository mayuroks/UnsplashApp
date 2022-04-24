package com.mayur.myimageapp.data

import com.mayur.myimageapp.data.imageSearch.ImageRepository

object RepoProvider {
    val imageRepository by lazy { ImageRepository() }
}