package com.bezgrebelnygregory.testapp.core.repository

import com.bezgrebelnygregory.testapp.core.api.ProcessResult
import com.bezgrebelnygregory.testapp.core.common.DataSource
import com.bezgrebelnygregory.testapp.core.model.BreedModel
import com.bezgrebelnygregory.testapp.core.model.ImageModel
import kotlinx.coroutines.CoroutineScope

interface BreedRepo {
    suspend fun getSubBreeds(breed: String, result: ProcessResult<List<String>>)
    fun getImages(
        scope: CoroutineScope,
        breed: String,
        result: ProcessResult<List<ImageModel>>
    ): DataSource<List<ImageModel>>

    suspend fun getBreeds(result: ProcessResult<List<BreedModel>>)
}