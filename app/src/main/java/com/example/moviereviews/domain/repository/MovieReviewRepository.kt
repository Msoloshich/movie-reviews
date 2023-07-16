package com.example.moviereviews.domain.repository

import androidx.paging.PagingData
import com.example.moviereviews.domain.entity.Critic
import com.example.moviereviews.domain.entity.Review
import kotlinx.coroutines.flow.Flow

interface MovieReviewRepository {

    fun getCriticList(): Flow<List<Critic>>

    fun getCritic(name: String): Flow<Critic>

    suspend fun loadData()

    fun getPagedReviews(): Flow<PagingData<Review>>
}