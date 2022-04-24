package com.mayur.myimageapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest

const val TAG = "getPaletteFromImageUrl"

@SuppressLint("CheckResult")
fun getPaletteFromImageUrl(
    context: Context,
    imageUrl: String,
    onSuccess: (Palette) -> Unit,
    onError: (t: Throwable?) -> Unit
) {
    val loader = ImageLoader(context)
    val req = ImageRequest.Builder(context)
        .data(imageUrl)
        .target {
            val bitmap = (it as BitmapDrawable).bitmap
            var palette: Palette? = null
            try {
                palette = Palette.from(bitmap).generate()
            } catch (t: Throwable) {
                onError(t)
                t.printStackTrace()
                Log.i(TAG, "Throwable ${t.message}")
            }

            palette?.let {
                onSuccess(it)
                Log.i(TAG, "onSuccess Palette.from(bitmap).generate()")
            }
        }
        .allowHardware(false)
        .build()
    val disposable = loader.enqueue(req)
}