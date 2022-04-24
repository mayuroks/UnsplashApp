package com.mayur.myimageapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

@SuppressLint("CheckResult")
//fun Glide.Companion.getPaletteFromImageUrl(
fun getPaletteFromImageUrl(
    context: Context,
    imageUrl: String,
    onSuccess: (Palette) -> Unit,
    onError: (t: Throwable?) -> Unit
) {
    Glide.with(context).asBitmap().load(imageUrl)
        .addListener(object : RequestListener<Bitmap> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Bitmap>?,
                isFirstResource: Boolean
            ): Boolean {
                onError(e)
                return true
            }

            override fun onResourceReady(
                resource: Bitmap?,
                model: Any?,
                target: Target<Bitmap>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                resource?.let {
                    kotlin.runCatching {
                        onSuccess(Palette.from(it).generate())
                    }.onFailure { onError(it) }
                }

                return true
            }
        })
}