package com.bezgrebelnygregory.testapp.core.model

sealed class DataModel<T> {
    data class Success<T>(val data: T) : DataModel<T>()
    data class Error<T>(val desc: String) : DataModel<T>()
}