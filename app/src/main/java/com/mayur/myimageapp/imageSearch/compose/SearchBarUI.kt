package com.mayur.myimageapp.imageSearch.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.mayur.myimageapp.R
import com.mayur.myimageapp.imageSearch.ImageSearchViewModel

@Composable
fun SearchBarUI(
    modifier: Modifier = Modifier,
    viewModel: ImageSearchViewModel,
    button: @Composable () -> Unit,
) {
    var searchText by viewModel.searchText

    SearchBarUI(
        modifier = modifier,
        searchText = searchText,
        button = button,
        onSearchTextChange = { searchText = it }
    )
}

@Composable
fun SearchBarUI(
    modifier: Modifier = Modifier,
    searchText: String,
    button: @Composable () -> Unit,
    onSearchTextChange: (String) -> Unit
) {
    Card(
        modifier = modifier,
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp),
                    value = searchText,
                    onValueChange = onSearchTextChange,
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

            button()
        }
    }
}

@Preview
@Composable
fun SearchBarUIPreview() {
    SearchBarUI(
        searchText = "",
        button = {
            SearchButtonUI("Search", {})
        },
        onSearchTextChange = {}
    )
}