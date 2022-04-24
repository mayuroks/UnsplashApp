package com.mayur.myimageapp

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchButtonUI(
    text: String,
    onSearchClicked: () -> Unit
) {
    Button(
        modifier = Modifier.height(56.dp),
        shape = RoundedCornerShape(100.dp),
        onClick = { onSearchClicked() }
    ) {
        Text(text = text)
    }
}