package com.mayur.myimageapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
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
            ImagesSearchUI(
                viewModel = viewModel,
                searchResultItem = viewModel.searchResults.value?.results?.randomOrNull(),
                context = LocalContext.current,
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