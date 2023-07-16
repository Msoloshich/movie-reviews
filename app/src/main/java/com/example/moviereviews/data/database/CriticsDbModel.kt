package com.example.moviereviews.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("critics")
data class CriticsDbModel(
    @PrimaryKey
    val name: String,
    val bio: String,
    val photoUrl: String?,
    val status: String,
)
