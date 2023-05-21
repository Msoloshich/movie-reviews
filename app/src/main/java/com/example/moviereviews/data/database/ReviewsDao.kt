package com.example.moviereviews.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ReviewsDao {
    @Query("SELECT * FROM reviews ORDER BY publicationDate DESC")
    fun getReviewsList(): LiveData<List<ReviewsDbModel>>
}