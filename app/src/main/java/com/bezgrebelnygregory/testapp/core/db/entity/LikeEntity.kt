package com.bezgrebelnygregory.testapp.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "like")
data class LikeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val breed: String,
    val image: String
)