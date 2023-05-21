package com.example.moviereviews.domain.entity

data class Review (
    val id: Int,
    val imageUrl: String,
    val title: String,
    val reviewUrl: String,
    val shortDescription: String,
    val author: String,
    val publicationDate: String,
)