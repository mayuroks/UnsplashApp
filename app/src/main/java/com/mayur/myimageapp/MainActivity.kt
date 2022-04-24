package com.mayur.myimageapp

import android.os.Bundle
import android.widget.Toast
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

// TODO:
// error page for no internet or results
// test cases
// Navigation setup with bottom sheet + fragment + deeplink + params
// change primary colors
// refactor and restructure code - datalayer module, compose previews + ui code
// - calculate palette in viewModel instead of compose

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
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
    }


    // TODO test getPaletteFromImageUrl and refactor this at the end
//    private fun setupObservers() {
//        viewModel.searchResults.observe() {
//            firstItem?.let {
//
//                Glide.get(
//                    this,
//                    it.urls.thumb,
//                    onSuccess = { viewModel.palette.value = it },
//                    onError = { viewModel.palette.value = null }
//                )
//            }
//        }
//    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyImageAppTheme {

    }
}