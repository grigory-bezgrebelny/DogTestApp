package com.bezgrebelnygregory.testapp.core.api.resp

import com.google.gson.annotations.SerializedName

data class DataResp<T>(
    @SerializedName("message") val message: T,
    @SerializedName("status") val status: String
) {
    inline fun <E> map(body: (T) -> E): DataResp<E> = DataResp(body.invoke(message), status)
}