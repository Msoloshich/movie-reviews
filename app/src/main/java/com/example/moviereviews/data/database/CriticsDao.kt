package com.example.moviereviews.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CriticsDao {
    @Query("SELECT * FROM critics ORDER BY name ASC")
    fun getCriticsList(): Flow<List<CriticsDbModel>>

    @Query("SELECT * FROM critics WHERE name == :name LIMIT 1")
    fun getCritic(name: String): Flow<CriticsDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCriticsList(criticsList: List<CriticsDbModel>)
}