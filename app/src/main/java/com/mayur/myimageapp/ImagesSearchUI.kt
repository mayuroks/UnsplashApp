package com.mayur.myimageapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ImagesSearchUI(
    viewModel: ImageSearchViewModel,
    onSearchClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp)
                .alpha(0.6f),
            text = "UnSplash",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        SearchBarUI(
            modifier = Modifier.padding(top = 30.dp, bottom = 12.dp, start = 12.dp, end = 12.dp),
            viewModel = viewModel,
            button = {
                SearchButton(
                    text = "Search",
                    onSearchClicked = onSearchClicked
                )
            },
        )


        // TODO enable once pagination is ready
//        if (viewModel.searchResults.value?.size > 0) {
//            Text(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(12.dp)
//                    .alpha(0.6f),
//                text = "${viewModel.searchResults} images found",
//                fontSize = 14.sp,
//            )
//        }

        ImagesGridUI(viewModel = viewModel)
    }
}