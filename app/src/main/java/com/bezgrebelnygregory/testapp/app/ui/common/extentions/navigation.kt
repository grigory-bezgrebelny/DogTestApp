package com.bezgrebelnygregory.testapp.app.ui.common.extentions

import android.content.Intent
import android.os.Parcelable
import androidx.fragment.app.FragmentActivity
import com.bezgrebelnygregory.testapp.app.ui.common.ContextAware

inline fun <reified T : FragmentActivity> ContextAware.navigateTo(data: Parcelable? = null) {
    val intent = Intent(currentContext, T::class.java)
    if (data != null) intent.putExtra(T::class.java.name, data)
    currentContext.startActivity(intent)
}

fun <R : Parcelable> FragmentActivity.getActivityContent(): R? =
    intent.getParcelableExtra<R>(this::class.java.name)