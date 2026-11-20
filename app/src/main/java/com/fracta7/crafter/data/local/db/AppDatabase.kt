package com.fracta7.crafter.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fracta7.crafter.data.local.db.dao.ProjectDao
import com.fracta7.crafter.data.local.db.entity.ProjectEntity

@Database(
    entities = [ProjectEntity::class],
    version = 3,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}
