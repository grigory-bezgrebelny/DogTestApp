package com.bezgrebelnygregory.testapp.app.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import com.bezgrebelnygregory.testapp.R

interface ContextAware {

    val currentContext: Context

    fun showErrorAlert(message: String) {
        AlertDialog.Builder(currentContext).apply {
            setTitle(R.string.error)
            setMessage(message)
            setPositiveButton(android.R.string.ok, null)
            create()
            show()
        }
    }

    fun showShareImageDialog(image: Uri) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, image)
            type = "image/*"
        }
        currentContext.startActivity(
            Intent.createChooser(
                shareIntent,
                currentContext.getString(R.string.share_image)
            )
        )
    }
}