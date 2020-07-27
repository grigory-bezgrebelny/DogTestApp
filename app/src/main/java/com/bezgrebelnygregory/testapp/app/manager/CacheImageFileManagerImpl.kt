package com.bezgrebelnygregory.testapp.app.manager

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import com.bezgrebelnygregory.testapp.app.common.AppFileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class CacheImageFileManagerImpl(private val context: Context) : CacheImageFileManager {

    private val cacheDir =
        checkPath(context.cacheDir.absolutePath + File.separator + "shared_images" + File.separator)

    override suspend fun createAndGetUri(image: String): Uri? =
        withContext(Dispatchers.IO) {
            val requestOptions = RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)

            // получение изображения
            val bitmap = Glide.with(context)
                .asBitmap()
                .load(image)
                .apply(requestOptions)
                .submit()
                .get()

            return@withContext try {
                clearCache()
                val file = createCacheFile()
                val fileOutputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                fileOutputStream.close()
                FileProvider.getUriForFile(context, AppFileProvider.NAME, file)
            } catch (e: Exception) {
                Log.e(TAG, "createAndGetUri: ", e)
                null
            }
        }

    private fun clearCache() {
        val dir = File(cacheDir)
        dir.listFiles { file -> file.delete() }
    }

    private fun getTimestamp() =
        SimpleDateFormat(DATE_TIMESTAMP, Locale.getDefault()).format(Calendar.getInstance().time)

    private fun createCacheFile(): File {
        val file = File(cacheDir + "share_image_${getTimestamp()}.jpg")
        return file.apply {
            createNewFile()
        }
    }

    // проверка создана ли директория
    private fun checkPath(path: String): String {
        val dir = File(path)
        if (!dir.exists()) dir.mkdir()
        return path
    }

    companion object {
        private const val TAG = "CacheImageFileManager"
        private const val DATE_TIMESTAMP = "yyyyMMdd_HHmmss"
    }
}