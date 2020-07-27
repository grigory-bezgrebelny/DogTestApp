package com.bezgrebelnygregory.testapp.core.repository

import com.bezgrebelnygregory.testapp.app.common.IdModel
import com.bezgrebelnygregory.testapp.core.api.Api
import com.bezgrebelnygregory.testapp.core.api.ApiHelper
import com.bezgrebelnygregory.testapp.core.api.ProcessResult
import com.bezgrebelnygregory.testapp.core.common.DataSource
import com.bezgrebelnygregory.testapp.core.db.dao.LikeDao
import com.bezgrebelnygregory.testapp.core.mapper.BreedRespToBreedModelMapper
import com.bezgrebelnygregory.testapp.core.model.ApiModel
import com.bezgrebelnygregory.testapp.core.model.BreedModel
import com.bezgrebelnygregory.testapp.core.model.ImageModel
import com.bezgrebelnygregory.testapp.core.model.LikeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BreedRepoImpl(
    private val api: Api,
    private val apiHelper: ApiHelper,
    private val likeDao: LikeDao,
    private val breedRespToBreedModelMapper: BreedRespToBreedModelMapper
) : BreedRepo {

    override suspend fun getSubBreeds(breed: String, result: ProcessResult<List<String>>) {
        apiHelper.getResult({ api.getSubBreeds(breed) }, result)
    }

    override fun getImages(
        scope: CoroutineScope,
        breed: String,
        result: ProcessResult<List<ImageModel>>
    ): DataSource<List<ImageModel>> =
        object : DataSource<List<ImageModel>>() {

            private var likeList: List<LikeModel> = listOf()
            private var imageList: List<String> = listOf()

            init {
                collectDataFromDB()
                fetchData()
            }

            private fun collectDataFromDB() {
                scope.launch {
                    likeDao.getListByBreedFlow(breed).collect {
                        likeList = it
                        updateData()
                    }
                }
            }

            override fun fetchData() {
                scope.launch {
                    apiHelper.getResult({ api.getImages(breed) }) {
                        when (it) {
                            is ApiModel.Success -> {
                                imageList = it.data
                                updateData()
                            }
                            is ApiModel.Loading -> result.invoke(ApiModel.Loading(it.value))
                            is ApiModel.Error -> result.invoke(ApiModel.Error(it.desc))
                        }
                    }
                }
            }

            private fun updateData() {
                val resultList = imageList.map { image ->
                    val findData = likeList.find { it.image == image }

                    ImageModel(
                        findData?.id ?: IdModel.NO_ID,
                        image,
                        findData != null
                    )
                }

                setValue(resultList)
            }
        }

    override suspend fun getBreeds(result: ProcessResult<List<BreedModel>>) {
        apiHelper.getResult({ api.getBreeds().map { breedRespToBreedModelMapper.map(it) } }, result)
    }
}