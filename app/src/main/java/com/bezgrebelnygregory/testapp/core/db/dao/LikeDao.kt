package com.bezgrebelnygregory.testapp.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bezgrebelnygregory.testapp.core.db.entity.LikeEntity
import com.bezgrebelnygregory.testapp.core.model.BreedModel
import com.bezgrebelnygregory.testapp.core.model.LikeModel
import kotlinx.coroutines.flow.Flow

@Dao
interface LikeDao {

    @Query(
        """select breed as name, count(image) as count
        from `like`
        group by breed
        order by name"""
    )
    fun getBreedList(): LiveData<List<BreedModel>>

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

    @Query(
        """select *
        from `like`
        where breed = :breed"""
    )
    fun getListByBreedLive(breed: String): LiveData<List<LikeModel>>

    @Insert
    fun insert(data: LikeEntity): Long

    @Delete
    fun delete(data: LikeEntity): Int
}