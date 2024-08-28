package com.fracta7.crafter.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fracta7.crafter.data.local.db.entity.ItemEntity

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    suspend fun getAll(): List<ItemEntity>

    @Insert
    suspend fun insertAll(vararg answers: ItemEntity)

    @Delete
    suspend fun delete(answers: ItemEntity)

    @Query("DELETE FROM items")
    suspend fun deleteTable()
}