package com.fracta7.crafter.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fracta7.crafter.data.local.db.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects WHERE isDraft = 0 ORDER BY updatedAt DESC")
    fun observeSavedProjects(): Flow<List<ProjectEntity>>

    @Query("SELECT * FROM projects WHERE isDraft = 1 LIMIT 1")
    fun observeDraft(): Flow<ProjectEntity?>

    @Query("SELECT * FROM projects WHERE isDraft = 1 LIMIT 1")
    suspend fun getDraft(): ProjectEntity?

    @Query("SELECT * FROM projects WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): ProjectEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: ProjectEntity): Long

    @Update
    suspend fun update(entity: ProjectEntity)

    @Query("DELETE FROM projects WHERE id = :id")
    suspend fun deleteById(id: Long)
}
