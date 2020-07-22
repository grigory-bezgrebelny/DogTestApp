package com.bezgrebelnygregory.testapp.core.model

sealed class ApiModel<T, E> {
    data class Success<T, E>(val data: T) : ApiModel<T, E>()
    class Loading<T, E> : ApiModel<T, E>()
    data class Error<T, E>(val error: E) : ApiModel<T, E>()
}