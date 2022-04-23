package com.mayur.myimageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mayur.myimageapp.ui.theme.MyImageAppTheme

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
            Column(modifier = Modifier.fillMaxWidth()) {
                SearchBarUI(
                    viewModel = viewModel,
                    button = {
                        SearchButton(
                            text = "Search",
                            onSearchClicked = { viewModel.getSearchedImages() }
                        )
                    },
                )
                ImagesGridUI(viewModel = viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyImageAppTheme {

    }
}