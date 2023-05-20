package com.example.moviereviews.data.network.model.critic

import com.example.moviereviews.data.network.model.general.MultimediaDto
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class CriticResultDto(
    @SerializedName("display_name")
    @Expose
    private val displayName: String,

    @SerializedName("status")
    @Expose
    private val status: String,

    @SerializedName("bio")
    @Expose
    private val bio: String,

    @SerializedName("multimedia")
    @Expose
    private val multimedia: MultimediaDto? = null,
)