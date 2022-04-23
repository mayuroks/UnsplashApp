package com.mayur.myimageapp.data

import com.google.gson.annotations.SerializedName

class SearchResults(
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<SearchResultItem>
)

class SearchResultItem(
    val id: String,
    val likes: Int,
    val description: String,
    val color: String,
    val urls: ImageUrl,
)

class ImageUrl(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
)