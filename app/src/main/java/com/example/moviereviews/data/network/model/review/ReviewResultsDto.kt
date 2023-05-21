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
    @SerializedName("date_updated")
    @Expose
    val dateUpdated: String,
    @SerializedName("link")
    @Expose
    val link: ReviewLinkDto? = null,
    @SerializedName("multimedia")
    @Expose
    val multimedia: MultimediaDto? = null,
    val id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
