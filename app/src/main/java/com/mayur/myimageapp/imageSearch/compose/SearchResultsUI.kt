package com.mayur.myimageapp.imageSearch.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mayur.myimageapp.components.ErrorUI
import com.mayur.myimageapp.data.imageSearch.SearchResultItem
import com.mayur.myimageapp.imageSearch.ImageSearchViewModel
import com.skydoves.landscapist.glide.GlideImage


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesGridUI(
    viewModel: ImageSearchViewModel,
    showErrorToast: () -> Unit,
) {
    val searchResults by viewModel.searchResults.observeAsState()
    val showErrorToast by viewModel.showErrorToast.observeAsState()
    val showErrorUi by viewModel.showErrorUi.observeAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getSearchedImages()
    }

    if (showErrorToast == true) {
        showErrorToast()
    }

    if (showErrorUi == true)
        ErrorUI("Oops!", "Something went wrong")
    else
        ImagesGridUI(searchResults = searchResults?.results)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesGridUI(searchResults: List<SearchResultItem>?) {
    val listState = rememberLazyListState()

    if (searchResults.isNullOrEmpty().not()) {
        LaunchedEffect(key1 = searchResults?.getOrNull(0)?.id) {
            listState.scrollToItem(0)
        }

        LazyVerticalGrid(
            state = listState ,
            cells = GridCells.Adaptive(100.dp)
        ) {
            items(searchResults!!) { image ->
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
            .clickable { },
        elevation = 6.dp,
        shape = RoundedCornerShape(0.dp),
    ) {
        GlideImage(
            imageModel = image.urls.thumb
        )
    }
}