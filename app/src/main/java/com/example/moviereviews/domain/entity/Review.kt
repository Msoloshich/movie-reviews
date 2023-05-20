package com.example.moviereviews.domain.entity

data class Review (
    val id: String,
    val imageUrl: String,
    val title: String,
    val shortDescription: String,
    val author: String,
    val date: String,
)