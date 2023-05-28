package com.example.moviereviews.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReviewsDao {
    @Query("SELECT * FROM reviews ORDER BY publicationDate DESC")
    fun getReviewsList(): LiveData<List<ReviewsDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviewsList(reviewList: List<ReviewsDbModel>)
}