package com.bezgrebelnygregory.testapp.core.api

import android.util.Log
import com.bezgrebelnygregory.testapp.core.model.ApiModel
import com.bezgrebelnygregory.testapp.core.resp.DataResp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

typealias ProcessResult<T> = (ApiModel<T, ApiHelper.NetError>) -> Unit

class ApiHelper {

    suspend inline fun <T> getResult(
        noinline body: suspend () -> DataResp<T>,
        noinline result: (ApiModel<T, NetError>) -> Unit
    ) {
        result.invoke(ApiModel.Loading())
        try {
            withContext(Dispatchers.IO) {

                // todo
                // todo обработка ошибок здесь !!!
                body.invoke().message

                result.invoke(ApiModel.Success<T, NetError>(body.invoke().message))
            }
        } catch (e: Exception) {
            Log.e(TAG, "getResult: ", e)
            result.invoke(ApiModel.Error(NetError.Error(e.message.toString())))
        } finally {
            result.invoke(ApiModel.Loading())
        }
    }

    sealed class NetError {
        object NotFound : NetError() // todo
        data class Error(val desc: String) : NetError()
    }

    companion object {
        const val TAG = "ApiHelper"
    }
}