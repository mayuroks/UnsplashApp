package com.mayur.myimageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mayur.myimageapp.ui.theme.MyImageAppTheme
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.BitmapPalette

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
        setContentWithAppTheme  {
            val firstItem = viewModel.searchResults.value?.results?.getOrNull(0)

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
                onSearchClicked = onSearchClicked
            )
        }
    }

    private val onSearchClicked = fun() {
        viewModel.getSearchedImages()
        dismissKeyboard()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyImageAppTheme {

    }
}