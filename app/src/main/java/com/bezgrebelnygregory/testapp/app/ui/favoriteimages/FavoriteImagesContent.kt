package com.bezgrebelnygregory.testapp.app.ui.favoriteimages

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteImagesContent(
    val name: String
) : Parcelable