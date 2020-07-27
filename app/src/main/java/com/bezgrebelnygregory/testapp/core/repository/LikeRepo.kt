package com.bezgrebelnygregory.testapp.core.repository

import androidx.lifecycle.LiveData
import com.bezgrebelnygregory.testapp.core.model.BreedModel
import com.bezgrebelnygregory.testapp.core.model.DataModel
import com.bezgrebelnygregory.testapp.core.model.LikeModel
import kotlinx.coroutines.flow.Flow

interface LikeRepo {
    fun getBreedList(): LiveData<List<BreedModel>>
    fun getListByBreedFlow(breed: String): Flow<List<LikeModel>>?
    suspend fun getListByBreed(breed: String): DataModel<List<LikeModel>>
    suspend fun insert(data: LikeModel): DataModel<Long>
    suspend fun delete(data: LikeModel): DataModel<Int>
}