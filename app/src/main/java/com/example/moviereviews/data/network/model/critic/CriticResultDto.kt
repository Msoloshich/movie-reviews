package com.example.moviereviews.data.network.model.critic

import com.example.moviereviews.data.network.model.general.MultimediaDto
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class CriticResultDto(
    @SerializedName("display_name")
    @Expose
    val displayName: String,

    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("bio")
    @Expose
    val bio: String,

    @SerializedName("multimedia")
    @Expose
    val multimedia: MultimediaDto? = null,
    val id: Int = UNDEFINED_ID,
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}