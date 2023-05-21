package com.example.moviereviews.domain.repository

import androidx.lifecycle.LiveData
import com.example.moviereviews.domain.entity.Critic
import com.example.moviereviews.domain.entity.Review

interface MovieReviewRepository {
    fun getReviewList(): LiveData<List<Review>>

    fun getCriticList(): LiveData<List<Critic>>

    fun getCritic(id: Int): LiveData<Critic>
}