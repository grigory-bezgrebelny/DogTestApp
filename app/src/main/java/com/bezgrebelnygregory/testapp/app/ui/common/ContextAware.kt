package com.bezgrebelnygregory.testapp.app.ui.common

import android.content.Context
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
}