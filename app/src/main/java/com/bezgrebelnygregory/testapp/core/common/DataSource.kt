package com.bezgrebelnygregory.testapp.core.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class DataSource<T> {

    private val _dataList = MutableLiveData<T>()
    val dataList: LiveData<T>
        get() = _dataList

    protected fun setValue(value: T) {
        _dataList.postValue(value)
    }
}