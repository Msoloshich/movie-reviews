package com.example.moviereviews.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.moviereviews.domain.entity.Review
import com.example.moviereviews.domain.repository.MovieReviewRepository
import com.example.moviereviews.domain.usecases.GetPagedReviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class MovieReviewsViewModel @Inject constructor(
    repository: MovieReviewRepository
) : ViewModel() {
    private val getPagedReviewsUseCase = GetPagedReviewsUseCase(repository)

    val reviewsLiveData: Flow<PagingData<Review>> = getPagedReviewsUseCase()
}