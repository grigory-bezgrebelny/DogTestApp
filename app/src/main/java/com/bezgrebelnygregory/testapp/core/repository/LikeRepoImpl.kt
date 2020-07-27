package com.bezgrebelnygregory.testapp.core.repository

import com.bezgrebelnygregory.testapp.core.db.DbHelper
import com.bezgrebelnygregory.testapp.core.db.dao.LikeDao
import com.bezgrebelnygregory.testapp.core.mapper.LikeModelToLikeEntityMapper
import com.bezgrebelnygregory.testapp.core.model.DataModel
import com.bezgrebelnygregory.testapp.core.model.LikeModel
import kotlinx.coroutines.flow.Flow

class LikeRepoImpl(
    private val dbHelper: DbHelper,
    private val dao: LikeDao,
    private val likeModelToLikeEntityMapper: LikeModelToLikeEntityMapper
) : LikeRepo {

    override fun getListByBreedFlow(breed: String): Flow<List<LikeModel>>? =
        dbHelper.getResultFlow { dao.getListByBreedFlow(breed) }

    override suspend fun getListByBreed(breed: String): DataModel<List<LikeModel>> =
        dbHelper.getResult { dao.getListByBreed(breed) }

    override suspend fun insert(data: LikeModel): DataModel<Long> =
        dbHelper.getResult { dao.insert(likeModelToLikeEntityMapper.map(data)) }

    override suspend fun delete(data: LikeModel): DataModel<Int> =
        dbHelper.getResult { dao.delete(likeModelToLikeEntityMapper.map(data)) }
}