package com.example.moviereviews.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReviewsDao {
    @Query("SELECT * FROM reviews ORDER BY publicationDate DESC")
    fun pagingSource(): PagingSource<Int, ReviewsDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviewsList(reviewList: List<ReviewsDbModel>)

    @Query("DELETE FROM reviews")
    suspend fun clearAll()


    @Query("SELECT COUNT(id) FROM reviews")
    fun getCount(): Int
}