package com.example.moviereviews.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.example.moviereviews.data.repository.MovieReviewRepositoryImpl
import com.example.moviereviews.domain.entity.Review
import com.example.moviereviews.domain.usecases.GetPagedReviewsUseCase
import kotlinx.coroutines.flow.Flow


@OptIn(ExperimentalPagingApi::class)
class MainActivityViewModel(application: Application): AndroidViewModel(application) {
    private val repository = MovieReviewRepositoryImpl(application)

    private val getPagedReviewsUseCase = GetPagedReviewsUseCase(repository)

    val reviewsLiveData: Flow<PagingData<Review>> = getPagedReviewsUseCase()
}