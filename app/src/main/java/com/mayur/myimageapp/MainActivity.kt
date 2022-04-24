package com.mayur.myimageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mayur.myimageapp.data.RepoProvider
import com.mayur.myimageapp.imageSearch.ImageSearchViewModel
import com.mayur.myimageapp.imageSearch.compose.ImagesSearchUI
import com.mayur.myimageapp.ui.theme.MyImageAppTheme
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.BitmapPalette


class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ImageSearchViewModel> {
        ImageSearchViewModel.Factory(RepoProvider.imageRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentWithAppTheme {
            val searchResults = viewModel.searchResults.observeAsState()
            val firstItem = searchResults.value?.results?.getOrNull(0)

            firstItem?.let {
                GlideImage(
                    modifier = Modifier.size(0.dp),
                    imageModel = it.urls.thumb,
                    bitmapPalette = BitmapPalette { p ->
                        viewModel.palette.value = p
                    }
                )
            }

            ImagesSearchUI(
                viewModel = viewModel,
                onSearchClicked = onSearchClicked,
                showErrorToast = showErrorToast
            )
        }
    }

    private val onSearchClicked = fun() {
        viewModel.getSearchedImages()
        dismissKeyboard()
    }

    private val showErrorToast = fun() {
        showShortToast("Error in getting images")
    }

//    override fun onResume() {
//        super.onResume()
//        setupObservers()
//    }

    // TODO test getPaletteFromImageUrl and refactor this at the end
//    private fun setupObservers() {
//        viewModel.searchResults.observe(this) {
//            it?.results?.getOrNull(0)?.let { item ->
//                getPaletteFromImageUrl(
//                    this,
//                    item.urls.thumb,
//                    onSuccess = { palette ->
//                        viewModel.palette.value = palette
//                        Log.i(TAG, "viewModel.palette.value = palette")
//                    },
//                    onError = { viewModel.palette.value = null }
//                )
//            }
//        }
//    }

    companion object {
        const val TAG = "MainActivity"
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyImageAppTheme {

    }
}