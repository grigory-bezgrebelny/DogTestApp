package com.bezgrebelnygregory.testapp.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bezgrebelnygregory.testapp.core.db.dao.LikeDao
import com.bezgrebelnygregory.testapp.core.db.entity.LikeEntity

@Database(
    entities = [LikeEntity::class],
    version = AppDB.VERSION,
    exportSchema = true
)
abstract class AppDB : RoomDatabase() {

    abstract fun getLikeDao(): LikeDao

    companion object {
        const val VERSION = 1
        private const val NAME = "app_db"

        fun create(context: Context): AppDB =
            Room.databaseBuilder(context, AppDB::class.java, NAME).build()
    }
}