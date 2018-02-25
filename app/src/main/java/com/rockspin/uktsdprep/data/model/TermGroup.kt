package com.rockspin.uktsdprep.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TermGroup(
    val groupName: String,
    val terms: List<Term>
): Parcelable
