package com.jeric.metromart.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jeric.metromart.data.local.GithubEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubDao {

    @Query("SELECT * FROM metroMart")
    fun getAllGithubEntities(): Flow<List<GithubEntity>>

    @Query("SELECT * FROM metroMart WHERE id = :id")
    fun getGithubEntityById(id: Int): GithubEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubEntities(entities: List<GithubEntity>)

    @Query("DELETE FROM metroMart")
    suspend fun clearGithubEntities()
}