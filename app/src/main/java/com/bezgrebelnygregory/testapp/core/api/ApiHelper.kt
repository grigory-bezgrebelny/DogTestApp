package com.bezgrebelnygregory.testapp.core.api

import android.util.Log
import com.bezgrebelnygregory.testapp.core.api.resp.DataResp
import com.bezgrebelnygregory.testapp.core.model.ApiModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

typealias ProcessResult<T> = (ApiModel<T>) -> Unit

class ApiHelper {

    suspend inline fun <T> getResult(
        noinline body: suspend () -> DataResp<T>,
        noinline result: (ApiModel<T>) -> Unit
    ) {
        withContext(Dispatchers.Main) {
            result.invoke(ApiModel.Loading(true))
            try {
                withContext(Dispatchers.IO) {
                    val data = body.invoke().message
                    Log.d(TAG, data.toString())
                    withContext(Dispatchers.Main) {
                        result.invoke(ApiModel.Success(data))
                    }
                }
            } catch (e: CancellationException) {
                Log.e(TAG, "getResult: ", e)
            } catch (e: Exception) {
                Log.e(TAG, "getResult: ", e)
                result.invoke(ApiModel.Error(e.message.toString()))
            } finally {
                result.invoke(ApiModel.Loading(false))
            }
        }
    }

    companion object {
        const val TAG = "ApiHelper"
    }
}