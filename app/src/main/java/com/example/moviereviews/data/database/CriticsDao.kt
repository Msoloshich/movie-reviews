package com.example.moviereviews.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface CriticsDao {
    @Query("SELECT * FROM critics ORDER BY name DESC")
    fun getCriticsList(): LiveData<List<CriticsDbModel>>
}