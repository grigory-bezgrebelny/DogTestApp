package com.bezgrebelnygregory.testapp.app.ui.favoriteimages

import android.net.Uri

sealed class Event {
    data class Share(val uri: Uri?) : Event()
}