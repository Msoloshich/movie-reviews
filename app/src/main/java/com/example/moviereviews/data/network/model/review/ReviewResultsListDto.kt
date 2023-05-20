package com.example.moviereviews.data.network.model.review

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewResultsListDto(
    @SerializedName("results")
    @Expose
    val results: List<ReviewResultsDto>
)
