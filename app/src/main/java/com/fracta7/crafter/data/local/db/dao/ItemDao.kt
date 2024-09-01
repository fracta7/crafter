package com.fracta7.crafter.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fracta7.crafter.data.local.db.entity.ItemEntity
import com.fracta7.crafter.domain.model.ItemID

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    suspend fun getAll(): List<ItemEntity>

    @Insert
    suspend fun insertAll(vararg items: ItemEntity)

    @Query("DELETE FROM items WHERE id = :itemID")
    suspend fun delete(itemID: ItemID)

    @Query("DELETE FROM items")
    suspend fun deleteTable()
}