package com.bezgrebelnygregory.testapp.app.ui.common.extentions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bezgrebelnygregory.testapp.app.ui.common.SingleEvent
import com.bezgrebelnygregory.testapp.app.ui.common.SingleEventObserver

inline fun <T> Fragment.observe(data: LiveData<T?>, crossinline body: (T) -> Unit) {
    data.observe(viewLifecycleOwner, Observer {
        if (it != null) body.invoke(it)
    })
}

inline fun <T> Fragment.observeEvent(
    data: LiveData<SingleEvent<T>>,
    crossinline body: (T) -> Unit
) {
    data.observe(viewLifecycleOwner, SingleEventObserver {
        body.invoke(it)
    })
}