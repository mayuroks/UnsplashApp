package com.mayur.myimageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mayur.myimageapp.data.SearchResultItem
import com.mayur.myimageapp.ui.theme.MyImageAppTheme
import com.skydoves.landscapist.glide.GlideImage

// TODO: search bar with button
// paginated results
// grid shimmerView for loading
// FullScreen fragment (need navigation??) with image transition
// error page for no internet or results
// change primary colors
// test cases

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ImageSearchViewModel> { ImageSearchViewModel.Factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentWithAppTheme {
            ImageGridUI(viewModel = viewModel)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageGridUI(viewModel: ImageSearchViewModel) {
    val searchResults = viewModel.searchResults.collectAsLazyPagingItems()
    ImagesGridViewUI(searchResults = searchResults)
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesGridViewUI(searchResults: LazyPagingItems<SearchResultItem>?) {
    searchResults?.let { images ->
        LazyVerticalGrid(cells = GridCells.Adaptive(100.dp)) {
            items(images.itemSnapshotList.items) { image ->
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyImageAppTheme {

    }
}