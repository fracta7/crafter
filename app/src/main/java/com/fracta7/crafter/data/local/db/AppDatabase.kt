package com.fracta7.crafter.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fracta7.crafter.data.local.db.dao.CategoryDao
import com.fracta7.crafter.data.local.db.dao.ItemDao
import com.fracta7.crafter.data.local.db.dao.RecipeDao
import com.fracta7.crafter.data.local.db.dao.RecipeTypeDao
import com.fracta7.crafter.data.local.db.entity.CategoryEntity
import com.fracta7.crafter.data.local.db.entity.ItemEntity
import com.fracta7.crafter.data.local.db.entity.RecipeEntity
import com.fracta7.crafter.data.local.db.entity.RecipeTypeEntity

@Database(
    entities = [CategoryEntity::class, ItemEntity::class, RecipeEntity::class, RecipeTypeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun itemDao(): ItemDao
    abstract fun recipeDao(): RecipeDao
    abstract fun recipeTypeDao(): RecipeTypeDao
}