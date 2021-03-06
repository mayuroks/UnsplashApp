package com.mayur.myimageapp.components

import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette

fun getBgColor(palette: Palette?): Color {
    return if (palette != null) {
        when {
            palette.vibrantSwatch?.rgb != null -> Color(palette.vibrantSwatch!!.rgb)
            palette.lightVibrantSwatch?.rgb != null -> Color(palette.lightVibrantSwatch!!.rgb)
            else -> Color.White
        }
    } else Color.White
}

fun getTextColor(palette: Palette?): Color {
    return if (palette != null) {
        when {
            palette.lightVibrantSwatch?.bodyTextColor != null -> Color(palette.lightVibrantSwatch!!.bodyTextColor)
            palette.lightVibrantSwatch?.titleTextColor != null -> Color(palette.lightVibrantSwatch!!.titleTextColor)
            palette.vibrantSwatch?.titleTextColor != null -> Color(palette.vibrantSwatch!!.titleTextColor)
            else -> Color.Black
        }
    } else Color.Black
}