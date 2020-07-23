package com.bezgrebelnygregory.testapp.core.model

sealed class ApiModel<T> {
    data class Success<T>(val data: T) : ApiModel<T>()
    data class Loading<T>(val value: Boolean) : ApiModel<T>()
    data class Error<T>(val desc: String) : ApiModel<T>()
}