package com.example.moviereviews.data.mappers

import com.example.moviereviews.data.database.ReviewsDbModel
import com.example.moviereviews.data.network.model.review.ReviewResultsDto
import com.example.moviereviews.domain.entity.Review
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class ReviewsMapper {

    private fun dateStringToTimeStampDate (date: String): Long {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(date)?.time ?: 0L
    }

    private fun timeStampDateToStringDate (timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp)
        val date = Date(stamp.time)
        val formatDate = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())
        formatDate.timeZone = TimeZone.getDefault()
        return formatDate.format(date)
    }

    fun mapReviewDtoToDbModel (dto: ReviewResultsDto):ReviewsDbModel {
        with(dto) {
            return ReviewsDbModel(
                id = id,
                author = byline,
                imageUrl = multimedia?.src,
                publicationDate = dateStringToTimeStampDate(dto.dateUpdated),
                reviewUrl = link?.url,
                shortDescription = summaryShort,
                title = displayTitle,
            )
        }
    }

    fun mapReviewModelToEntity (model: ReviewsDbModel): Review {
        with(model) {
            return Review(
                id = id,
                author = author,
                imageUrl = imageUrl,
                publicationDate = timeStampDateToStringDate(model.publicationDate),
                reviewUrl = reviewUrl,
                shortDescription = shortDescription,
                title = title,
            )
        }
    }
}