package com.bezgrebelnygregory.testapp.core.repository

import com.bezgrebelnygregory.testapp.core.api.ProcessResult
import com.bezgrebelnygregory.testapp.core.model.BreedModel

interface BreedRepo {
    suspend fun getSubBreeds(breed: String, result: ProcessResult<List<String>>)
    suspend fun getImages(breed: String, result: ProcessResult<List<String>>)
    suspend fun getBreeds(result: ProcessResult<List<BreedModel>>)
}