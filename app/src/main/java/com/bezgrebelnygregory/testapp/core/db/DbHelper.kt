package com.bezgrebelnygregory.testapp.core.db

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bezgrebelnygregory.testapp.core.model.DataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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

    inline fun <T> getResultLive(
        body: () -> LiveData<T>
    ): LiveData<T> =
        try {
            body.invoke()
        } catch (e: Exception) {
            Log.e(TAG, "getResult: ", e)
            MutableLiveData<T>()
        }

    inline fun <T> getResultFlow(
        body: () -> Flow<T>
    ): Flow<T>? =
        try {
            body.invoke()
        } catch (e: Exception) {
            Log.e(TAG, "getResult: ", e)
            null
        }

    companion object {
        const val TAG = "ApiHelper"
    }
}