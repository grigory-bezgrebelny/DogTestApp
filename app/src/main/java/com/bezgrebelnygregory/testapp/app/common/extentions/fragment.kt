package com.bezgrebelnygregory.testapp.app.common.extentions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bezgrebelnygregory.testapp.app.common.SingleEvent
import com.bezgrebelnygregory.testapp.app.common.SingleEventObserver

inline fun <T> Fragment.observe(data: LiveData<T>, crossinline body: (T) -> Unit) {
    data.observe(viewLifecycleOwner, Observer {
        body.invoke(it)
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