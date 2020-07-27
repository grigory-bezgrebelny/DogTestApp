package com.bezgrebelnygregory.testapp.core.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bezgrebelnygregory.testapp.core.db.entity.LikeEntity
import com.bezgrebelnygregory.testapp.core.model.LikeModel
import kotlinx.coroutines.flow.Flow

@Dao
interface LikeDao {

    @Query(
        """select *
        from `like`
        where breed = :breed"""
    )
    fun getListByBreedFlow(breed: String): Flow<List<LikeModel>>

    @Query(
        """select *
        from `like`
        where breed = :breed"""
    )
    suspend fun getListByBreed(breed: String): List<LikeModel>

    @Insert
    fun insert(data: LikeEntity): Long

    @Delete
    fun delete(data: LikeEntity): Int
}