package com.bezgrebelnygregory.testapp.app.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class EventVM<E> : ViewModel() {
    private val _event = MutableLiveData<SingleEvent<E>>()
    val event: LiveData<SingleEvent<E>>
        get() = _event

    protected fun setEvent(event: E) {
        _event.value = SingleEvent(event)
    }
}