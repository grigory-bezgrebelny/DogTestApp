package com.bezgrebelnygregory.testapp.core.repository

import com.bezgrebelnygregory.testapp.core.api.ProcessResult
import com.bezgrebelnygregory.testapp.core.common.NetDataSource
import com.bezgrebelnygregory.testapp.core.model.BreedModel
import com.bezgrebelnygregory.testapp.core.model.ImageModel
import kotlinx.coroutines.CoroutineScope

interface BreedRepo {
    suspend fun getSubBreeds(breed: String, result: ProcessResult<List<String>>)
    fun getImages(
        scope: CoroutineScope,
        breed1: String,
        breed2: String?,
        result: ProcessResult<List<ImageModel>>
    ): NetDataSource<List<ImageModel>>

    suspend fun getBreeds(result: ProcessResult<List<BreedModel>>)
}