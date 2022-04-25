package com.mayur.myimageapp.imageSearch.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mayur.myimageapp.imageSearch.ImageSearchViewModel

@Composable
fun ImagesSearchUI(
    viewModel: ImageSearchViewModel,
    onSearchClicked: () -> Unit,
    showErrorToast: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val palette by viewModel.palette.observeAsState()
        val colorState by animateColorAsState(
            targetValue = if (palette != null) {
                when {
                    palette!!.darkVibrantSwatch?.bodyTextColor != null -> Color(palette!!.darkVibrantSwatch!!.bodyTextColor)
                    palette!!.darkVibrantSwatch?.titleTextColor != null -> Color(palette!!.darkVibrantSwatch!!.titleTextColor)
                    palette!!.darkMutedSwatch?.bodyTextColor != null -> Color(palette!!.darkMutedSwatch!!.bodyTextColor)
                    else -> Color.White
                }
            }
            else Color.White,
            animationSpec = tween(
                durationMillis = 700,
                easing = LinearEasing
            )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorState)
        ) {
            val colorState by animateColorAsState(
                targetValue = if (palette != null) {
                    when {
                        palette!!.lightVibrantSwatch?.rgb != null -> Color(palette!!.lightVibrantSwatch!!.bodyTextColor)
                        palette!!.lightVibrantSwatch?.rgb != null -> Color(palette!!.lightVibrantSwatch!!.titleTextColor)
                        palette!!.lightMutedSwatch?.rgb != null -> Color(palette!!.lightMutedSwatch!!.bodyTextColor)
                        else -> Color.Black
                    }
                }
                else Color.Black,
                animationSpec = tween(
                    durationMillis = 700,
                    easing = LinearEasing
                )
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp)
                    .alpha(0.6f),
                text = "UnSplash",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = colorState
            )
            SearchBarUI(
                modifier = Modifier.padding(
                    top = 30.dp,
                    bottom = 12.dp,
                    start = 24.dp,
                    end = 12.dp
                ),
                viewModel = viewModel,
                button = {
                    SearchButtonUI(
                        text = "Search",
                        onSearchClicked = onSearchClicked
                    )
                },
            )
        }

        ImagesGridUI(viewModel = viewModel, showErrorToast = showErrorToast)
    }
}