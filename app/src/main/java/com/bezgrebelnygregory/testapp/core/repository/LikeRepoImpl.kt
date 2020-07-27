package com.bezgrebelnygregory.testapp.core.repository

import androidx.lifecycle.LiveData
import com.bezgrebelnygregory.testapp.core.common.DataSource
import com.bezgrebelnygregory.testapp.core.db.DbHelper
import com.bezgrebelnygregory.testapp.core.db.dao.LikeDao
import com.bezgrebelnygregory.testapp.core.mapper.LikeModelToLikeEntityMapper
import com.bezgrebelnygregory.testapp.core.model.BreedModel
import com.bezgrebelnygregory.testapp.core.model.DataModel
import com.bezgrebelnygregory.testapp.core.model.ImageModel
import com.bezgrebelnygregory.testapp.core.model.LikeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LikeRepoImpl(
    private val dbHelper: DbHelper,
    private val dao: LikeDao,
    private val likeModelToLikeEntityMapper: LikeModelToLikeEntityMapper
) : LikeRepo {

    override fun getBreedListLive(): LiveData<List<BreedModel>> =
        dbHelper.getResultLive { dao.getBreedList() }

    override fun getListByBreed(
        scope: CoroutineScope,
        breed: String
    ): DataSource<List<ImageModel>> =
        object : DataSource<List<ImageModel>>() {

            private var origList: List<LikeModel> = listOf()

            init {
                scope.launch {
                    origList = dao.getListByBreed(breed)
                    dao.getListByBreedFlow(breed).collect { list ->

                        val resultList = origList.map { model ->
                            val isFavorite = list.find { item -> item.id == model.id } != null

                            ImageModel(
                                model.id,
                                model.image,
                                isFavorite
                            )
                        }

                        setValue(resultList)
                    }
                }
            }
        }

    override suspend fun insert(data: LikeModel): DataModel<Long> =
        dbHelper.getResult { dao.insert(likeModelToLikeEntityMapper.map(data)) }

    override suspend fun delete(data: LikeModel): DataModel<Int> =
        dbHelper.getResult { dao.delete(likeModelToLikeEntityMapper.map(data)) }
}