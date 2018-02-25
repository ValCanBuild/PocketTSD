package com.rockspin.uktsdprep.util

import android.content.Context

object Utils {

    fun readStringFromAssets(context: Context, assetName: String): String {
        val inputStream = context.assets.open(assetName)
        return inputStream.bufferedReader().use { it.readText() }
    }
}