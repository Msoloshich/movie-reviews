package com.example.moviereviews.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.moviereviews.domain.entity.Review
import com.example.moviereviews.domain.repository.MovieReviewRepository
import com.example.moviereviews.domain.usecases.GetCriticListUseCase
import com.example.moviereviews.domain.usecases.GetPagedReviewsUseCase
import com.example.moviereviews.domain.usecases.LoadDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieReviewsViewModel @Inject constructor(
    repository: MovieReviewRepository
) : ViewModel() {
    private val getPagedReviewsUseCase = GetPagedReviewsUseCase(repository)
    private val loadData = LoadDataUseCase(repository)
    val getCriticsList = GetCriticListUseCase(repository)



    val reviewsLiveData: Flow<PagingData<Review>> = getPagedReviewsUseCase()

    init {
        viewModelScope.launch {
            loadData()
        }
    }
}