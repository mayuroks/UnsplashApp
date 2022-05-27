package com.mayur.myimageapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.mayur.myimageapp.components.getBgColor
import com.mayur.myimageapp.components.getTextColor
import com.mayur.myimageapp.components.setContentWithAppTheme
import com.mayur.myimageapp.data.RepoProvider
import com.mayur.myimageapp.imageSearch.ImageSearchViewModel
import com.mayur.myimageapp.imageSearch.compose.ImagesSearchUI
import com.mayur.myimageapp.ui.theme.MyImageAppTheme
import com.mayur.myimageapp.utils.getPaletteFromImageUrl


class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ImageSearchViewModel> {
        ImageSearchViewModel.Factory(RepoProvider.imageRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentWithAppTheme {
            val palette by viewModel.palette
            val searchResultItem = viewModel.searchResults.value?.results?.randomOrNull()

            ImagesSearchUI(
                viewModel = viewModel,
                searchResultItem = searchResultItem,
                bgColor = getBgColor(palette),
                textColor = getTextColor(palette),
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

    override fun onResume() {
        super.onResume()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.searchResults.observe(this) {
            it?.results?.randomOrNull()?.let { item ->
                getPaletteFromImageUrl(
                    context = this@MainActivity,
                    imageUrl = item.urls.thumb,
                    onSuccess = { palette ->
                        viewModel.palette.value = palette
                        Log.i(TAG, "viewModel.palette.value = palette")
                    },
                    onError = { viewModel.palette.value = null }
                )
            }
        }
    }

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