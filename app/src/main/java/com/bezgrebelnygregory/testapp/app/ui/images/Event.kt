package com.bezgrebelnygregory.testapp.app.ui.images

import android.net.Uri

sealed class Event {
    data class Loading(val value: Boolean) : Event()
    data class Error(val desc: String) : Event()
    data class Share(val uri: Uri?) : Event()
}