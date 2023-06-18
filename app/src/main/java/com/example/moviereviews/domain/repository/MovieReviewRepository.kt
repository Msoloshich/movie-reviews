package com.example.moviereviews.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.moviereviews.domain.entity.Critic
import com.example.moviereviews.domain.entity.Review
import kotlinx.coroutines.flow.Flow

interface MovieReviewRepository {

    fun getCriticList(): LiveData<List<Critic>>

    fun getCritic(id: Int): LiveData<Critic>

    suspend fun loadData()

    fun getPagedReviews(): Flow<PagingData<Review>>
}