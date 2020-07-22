package com.bezgrebelnygregory.testapp.core.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.bezgrebelnygregory.testapp.core.db.entity.LikeEntity

@Dao
interface LikeDao {
    @Insert
    fun insert(data: LikeEntity): Long

    @Delete
    fun delete(data: LikeEntity): Int
}