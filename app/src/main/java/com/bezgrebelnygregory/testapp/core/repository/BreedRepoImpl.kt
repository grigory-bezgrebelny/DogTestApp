package com.bezgrebelnygregory.testapp.core.repository

import com.bezgrebelnygregory.testapp.core.api.Api
import com.bezgrebelnygregory.testapp.core.api.ApiHelper
import com.bezgrebelnygregory.testapp.core.api.ProcessResult
import com.bezgrebelnygregory.testapp.core.mapper.BreedRespToBreedModelMapper
import com.bezgrebelnygregory.testapp.core.model.BreedModel

class BreedRepoImpl(
    private val api: Api,
    private val apiHelper: ApiHelper,
    private val breedRespToBreedModelMapper: BreedRespToBreedModelMapper
) : BreedRepo {

    override suspend fun getSubBreeds(breed: String, result: ProcessResult<List<String>>) {
        apiHelper.getResult({ api.getSubBreeds(breed) }, result)
    }

    override suspend fun getImages(breed: String, result: ProcessResult<List<String>>) {
        apiHelper.getResult({ api.getImages(breed) }, result)
    }

    override suspend fun getBreeds(result: ProcessResult<BreedModel>) {
        apiHelper.getResult({
            api.getBreeds().map { breedRespToBreedModelMapper.map(it) }
        }, result)
    }
}