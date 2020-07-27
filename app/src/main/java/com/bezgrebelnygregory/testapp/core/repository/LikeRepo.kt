package com.bezgrebelnygregory.testapp.core.repository

import androidx.lifecycle.LiveData
import com.bezgrebelnygregory.testapp.core.common.DataSource
import com.bezgrebelnygregory.testapp.core.model.BreedModel
import com.bezgrebelnygregory.testapp.core.model.DataModel
import com.bezgrebelnygregory.testapp.core.model.ImageModel
import com.bezgrebelnygregory.testapp.core.model.LikeModel
import kotlinx.coroutines.CoroutineScope

interface LikeRepo {
    fun getBreedListLive(): LiveData<List<BreedModel>>
    fun getListByBreed(scope: CoroutineScope, breed: String): DataSource<List<ImageModel>>
    suspend fun insert(data: LikeModel): DataModel<Long>
    suspend fun delete(data: LikeModel): DataModel<Int>
}