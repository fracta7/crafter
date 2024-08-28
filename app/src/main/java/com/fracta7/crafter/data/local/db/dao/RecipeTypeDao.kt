package com.fracta7.crafter.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fracta7.crafter.data.local.db.entity.RecipeTypeEntity

@Dao
interface RecipeTypeDao {
    @Query("SELECT * FROM recipe_types")
    suspend fun getAll(): List<RecipeTypeEntity>

    @Insert
    suspend fun insertAll(vararg answers: RecipeTypeEntity)

    @Delete
    suspend fun delete(answers: RecipeTypeEntity)

    @Query("DELETE FROM recipe_types")
    suspend fun deleteTable()
}