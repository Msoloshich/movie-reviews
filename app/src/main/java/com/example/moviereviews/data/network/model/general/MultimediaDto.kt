package com.example.moviereviews.data.network.model.general

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MultimediaDto(
    @SerializedName("src")
    @Expose
    val src: String
)
