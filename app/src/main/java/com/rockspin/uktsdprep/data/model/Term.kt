package com.rockspin.uktsdprep.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Term(
    val phrase: String,
    val meaning: String
): Parcelable