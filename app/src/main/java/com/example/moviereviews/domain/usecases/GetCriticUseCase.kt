package com.example.moviereviews.domain.usecases

import com.example.moviereviews.domain.repository.MovieReviewRepository

class GetCriticUseCase(private val repository: MovieReviewRepository) {
    operator fun invoke(id: Int) = repository.getCritic(id)
}