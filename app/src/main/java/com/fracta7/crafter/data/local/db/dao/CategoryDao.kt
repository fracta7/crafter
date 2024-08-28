package com.fracta7.crafter.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fracta7.crafter.data.local.db.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    suspend fun getAll(): List<CategoryEntity>

    @Insert
    suspend fun insertAll(vararg answers: CategoryEntity)

    @Delete
    suspend fun delete(answers: CategoryEntity)

    @Query("DELETE FROM categories")
    suspend fun deleteTable()
}