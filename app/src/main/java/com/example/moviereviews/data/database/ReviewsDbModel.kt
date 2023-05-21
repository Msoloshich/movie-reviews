package com.example.moviereviews.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("reviews")
data class ReviewsDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val imageUrl: String?,
    val title: String,
    val reviewUrl: String?,
    val shortDescription: String,
    val author: String,
    val publicationDate: Long,
)
