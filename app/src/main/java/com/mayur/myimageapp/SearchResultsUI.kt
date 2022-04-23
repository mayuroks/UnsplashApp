package com.mayur.myimageapp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mayur.myimageapp.data.SearchResultItem
import com.skydoves.landscapist.glide.GlideImage


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesGridUI(viewModel: ImageSearchViewModel) {
    val searchResults by viewModel.searchResults

    LaunchedEffect(key1 = Unit) {
        viewModel.getSearchedImages()
    }

    ImagesGridUI(searchResults = searchResults)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesGridUI(searchResults: List<SearchResultItem>?) {
    searchResults?.let { images ->
        LazyVerticalGrid(cells = GridCells.Adaptive(100.dp)) {
            items(images) { image ->
                ImageItemUI(image)
            }
        }
    }
}

@Composable
fun ImageItemUI(image: SearchResultItem) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .padding(0.5.dp)
            .clickable { },
        elevation = 6.dp,
        shape = RoundedCornerShape(0.dp),
    ) {
        GlideImage(
            imageModel = image.urls.thumb
        )
    }
}