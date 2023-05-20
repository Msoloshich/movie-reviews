package com.example.moviereviews.data.network.model.critic

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CriticResultsListDto (
    @SerializedName("results")
    @Expose
    val results: List<CriticResultDto>
)