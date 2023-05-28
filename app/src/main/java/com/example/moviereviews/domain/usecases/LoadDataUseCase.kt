package com.example.moviereviews.domain.usecases

import com.example.moviereviews.domain.repository.MovieReviewRepository

class LoadDataUseCase (private val repository: MovieReviewRepository){
    suspend operator fun invoke() = repository.loadData()
}