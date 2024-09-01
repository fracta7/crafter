package com.fracta7.crafter.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fracta7.crafter.data.local.db.entity.RecipeEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    suspend fun getAll(): List<RecipeEntity>

    @Insert
    suspend fun insertAll(vararg recipes: RecipeEntity)

    @Delete
    suspend fun delete(recipe: RecipeEntity)

    @Query("DELETE FROM recipes")
    suspend fun deleteTable()
}