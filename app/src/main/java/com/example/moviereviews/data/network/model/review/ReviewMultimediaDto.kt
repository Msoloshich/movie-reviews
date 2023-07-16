package com.example.moviereviews.data.network.model.review

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewMultimediaDto(
    @SerializedName("src")
    @Expose
    val src: String
)
