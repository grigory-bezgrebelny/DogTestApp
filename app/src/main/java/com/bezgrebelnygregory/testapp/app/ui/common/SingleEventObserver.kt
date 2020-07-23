package com.bezgrebelnygregory.testapp.app.ui.common

import androidx.lifecycle.Observer

/** получение значения из SingleEvent */
class SingleEventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) :
    Observer<SingleEvent<T>> {
    override fun onChanged(event: SingleEvent<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}