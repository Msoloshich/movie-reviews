package com.example.moviereviews.data.network.model.review

import com.example.moviereviews.data.network.model.general.MultimediaDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewResultsDto(
    @SerializedName("display_title")
    @Expose
    val displayTitle: String,
    @SerializedName("byline")
    @Expose
    val byline: String,
    @SerializedName("summary_short")
    @Expose
    val summaryShort: String,
    @SerializedName("publication_date")
    @Expose
    val publicationDate: String,
    @SerializedName("link")
    @Expose
    val link: ReviewLinkDto? = null,
    @SerializedName("multimedia")
    @Expose
    val multimedia: MultimediaDto? = null
)
