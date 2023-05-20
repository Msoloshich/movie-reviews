package com.example.moviereviews.domain.usecases

import com.example.moviereviews.domain.repository.MovieReviewRepository

class GetCriticListUseCase(private val repository: MovieReviewRepository) {
    operator fun invoke() = repository.getCriticList()
}