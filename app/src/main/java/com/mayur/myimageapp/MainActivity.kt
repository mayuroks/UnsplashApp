package com.mayur.myimageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        setContentWithAppTheme {
            Column(modifier = Modifier.fillMaxWidth()) {
                SearchButtonUI(viewModel = viewModel)
                ImagesGridUI(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun SearchButtonUI(viewModel: ImageSearchViewModel) {
    var searchText by viewModel.searchText

    Card(
        modifier = Modifier.padding(top = 80.dp, bottom = 24.dp, start = 12.dp, end = 12.dp),
        shape = RoundedCornerShape(100.dp),
        elevation = 8.dp
    ) {

        Box(
            modifier = Modifier.height(56.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                modifier = Modifier.background(Color(0xFFF5F5F5)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth().padding(0.dp),
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    placeholder = { Text("Beagle, Fruits, Cars") },
                    leadingIcon = {
                        Image(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.ic_icons8_search),
                            contentDescription = ""
                        )
                    }
                )
            }

            Button(
                modifier = Modifier.height(56.dp),
                shape = RoundedCornerShape(100.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Search")
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