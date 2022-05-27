package com.mayur.myimageapp.imageSearch.compose

import android.content.Context
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.mayur.myimageapp.MainActivity
import com.mayur.myimageapp.components.getBgColor
import com.mayur.myimageapp.components.getTextColor
import com.mayur.myimageapp.data.imageSearch.SearchResultItem
import com.mayur.myimageapp.imageSearch.ImageSearchViewModel
import com.mayur.myimageapp.utils.getPaletteFromImageUrl
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ImagesSearchUI(
    viewModel: ImageSearchViewModel,
    searchResultItem: SearchResultItem?,
    context: Context,
    onSearchClicked: () -> Unit,
    showErrorToast: () -> Unit
) {
    val searchResults by viewModel.searchResults
    searchResults?.results?.randomOrNull()?.let { item ->
        getPaletteFromImageUrl(
            context = context,
            imageUrl = item.urls.thumb,
            onSuccess = { palette ->
                viewModel.palette.value = palette
                Log.i(MainActivity.TAG, "viewModel.palette.value = palette")
            },
            onError = { viewModel.palette.value = null }
        )
    }

    ImagesSearchUI(
        viewModel = viewModel,
        searchResultItem = searchResultItem,
        onSearchClicked = onSearchClicked,
        showErrorToast = showErrorToast
    )
}


@Composable
fun ImagesSearchUI(
    viewModel: ImageSearchViewModel,
    searchResultItem: SearchResultItem?,
    onSearchClicked: () -> Unit,
    showErrorToast: () -> Unit
) {
    val palette by viewModel.palette
    val bgColor = getBgColor(palette)
    val textColor = getTextColor(palette)

    Box {
        val alphaState by animateFloatAsState(
            targetValue = 0.4f,
            animationSpec = tween(
                durationMillis = 3000,
                easing = LinearEasing
            )
        )

        GlideImage(
            imageModel = searchResultItem?.urls?.small,
            alpha = alphaState
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Log.i("ImagesSearchUI", "bgColor ${bgColor.value}")
            val colorState by animateColorAsState(
                targetValue = bgColor.copy(alpha = 0.4f),
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
                val textColorState by animateColorAsState(
                    targetValue = textColor,
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
                    color = textColorState
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
}