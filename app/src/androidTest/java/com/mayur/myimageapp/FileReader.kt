package com.mayur.myimageapp

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import java.io.IOException
import java.io.InputStreamReader

object FileReader {
    private var TAG: String = this::class.java.simpleName

    fun readStringFromFile(fileName: String): String {
        try {
            val inputStream = InstrumentationRegistry.getInstrumentation()
                .context.resources.assets.open(fileName)
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, "UTF-8")
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        } catch (e: IOException) {
            Log.i(TAG,"exception ${e.message}")
            e.printStackTrace()
            throw e
        }
    }
}