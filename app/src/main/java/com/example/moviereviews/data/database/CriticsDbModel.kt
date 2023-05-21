package com.example.moviereviews.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("critics")
data class CriticsDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bio: String,
    val photoUrl: String?,
    val name: String,
    val status: String,
)
