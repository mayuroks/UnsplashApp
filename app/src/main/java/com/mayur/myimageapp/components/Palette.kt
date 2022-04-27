package com.mayur.myimageapp.components

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette

// TODO clean palette code
fun getBgColor(palette: Palette?): Color {
    return if (palette != null) {
        Log.i("ImagesSearchUI", "vibrantSwatch ${palette!!.vibrantSwatch?.rgb}")
        Log.i("ImagesSearchUI", "lightVibrantSwatch ${palette!!.lightVibrantSwatch?.rgb}")
        when {
            palette!!.vibrantSwatch?.rgb != null -> Color(palette!!.vibrantSwatch!!.rgb)
            palette!!.lightVibrantSwatch?.rgb != null -> Color(palette!!.lightVibrantSwatch!!.rgb)
            else -> {
                Log.i("ImagesSearchUI", "Color 1 white")
                Color.White
            }
        }
    } else {
        Log.i("ImagesSearchUI", "Color 2 white")
        Color.White
    }
}

fun getTextColor(palette: Palette?): Color {
    return if (palette != null) {
        when {
            palette!!.lightVibrantSwatch?.bodyTextColor != null -> Color(palette!!.lightVibrantSwatch!!.bodyTextColor)
            palette!!.lightVibrantSwatch?.titleTextColor != null -> Color(palette!!.lightVibrantSwatch!!.titleTextColor)
            palette!!.vibrantSwatch?.titleTextColor != null -> Color(palette!!.vibrantSwatch!!.titleTextColor)
            else -> Color.Black
        }
    } else Color.Black
}