package com.bezgrebelnygregory.testapp.app.manager

import android.net.Uri

interface CacheImageFileManager {
    suspend fun createAndGetUri(image: String): Uri?
}