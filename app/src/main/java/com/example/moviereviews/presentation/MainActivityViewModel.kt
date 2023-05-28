package com.example.moviereviews.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereviews.data.repository.MovieReviewRepositoryImpl
import com.example.moviereviews.domain.usecases.GetReviewListUseCase
import com.example.moviereviews.domain.usecases.LoadDataUseCase
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application): AndroidViewModel(application) {
    private val repository = MovieReviewRepositoryImpl(application)

    private val getReviewListUseCase = GetReviewListUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)


    val reviewList = getReviewListUseCase()

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

}