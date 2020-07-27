package com.bezgrebelnygregory.testapp.app.ui.images

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImagesContent(
    val breed1: String,
    val breed2: String?
) : Parcelable