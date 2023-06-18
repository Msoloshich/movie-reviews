package com.example.moviereviews.domain.usecases

import com.example.moviereviews.domain.repository.MovieReviewRepository

class GetPagedReviewsUseCase(private val reviewRepository: MovieReviewRepository) {
    operator fun invoke() =  reviewRepository.getPagedReviews()
}