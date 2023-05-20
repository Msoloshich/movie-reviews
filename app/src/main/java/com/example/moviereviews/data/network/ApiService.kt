package com.example.moviereviews.data.network

import com.example.moviereviews.BuildConfig
import com.example.moviereviews.data.network.model.critic.CriticResultsListDto
import com.example.moviereviews.data.network.model.review.ReviewResultsListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("reviews/search.json")
    suspend fun getReviews(
        @Query(QUERY_API_KEY) apiKey: String = BuildConfig.API_KEY,
    ): ReviewResultsListDto

    @GET("critics/all.json")
    suspend fun getCritics(
        @Query(QUERY_API_KEY) apiKey: String = BuildConfig.API_KEY,
    ): CriticResultsListDto


    companion object {
        private const val QUERY_API_KEY = "api-key"
    }

}