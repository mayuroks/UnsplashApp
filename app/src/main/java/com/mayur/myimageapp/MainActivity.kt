package com.mayur.myimageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mayur.myimageapp.ui.theme.MyImageAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ImageViewModel> { ImageViewModel.Factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentWithAppTheme {
            ImageGridUI(viewModel = viewModel)
        }
    }
}


@Composable
fun ImageGridUI(viewModel: ImageViewModel) {
    LaunchedEffect(Unit) {
        viewModel.getImages("iphone")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyImageAppTheme {

    }
}