package com.bezgrebelnygregory.testapp.core.db

import android.util.Log
import com.bezgrebelnygregory.testapp.core.model.DataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DbHelper {

    suspend inline fun <T> getResult(
        noinline body: suspend () -> T
    ): DataModel<T> =
        try {
            withContext(Dispatchers.IO) {
                DataModel.Success(body.invoke())
            }
        } catch (e: Exception) {
            Log.e(TAG, "getResult: ", e)
            DataModel.Error(e.message.toString())
        }

    companion object {
        const val TAG = "ApiHelper"
    }
}