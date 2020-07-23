package com.bezgrebelnygregory.testapp.app.ui.images

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImagesContent(
    val name: String
) : Parcelable