package com.example.moviereviews.di

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import com.example.moviereviews.data.database.AppDatabase
import com.example.moviereviews.data.network.ApiFactory
import com.example.moviereviews.data.network.ApiService
import com.example.moviereviews.data.repository.MovieReviewRepositoryImpl
import com.example.moviereviews.domain.repository.MovieReviewRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApi(): ApiService {
        return ApiFactory.apiService
    }

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return AppDatabase.getInstance(application)
    }


    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideMovieReviewRepository(apiService: ApiService, database: AppDatabase): MovieReviewRepository {
        return MovieReviewRepositoryImpl(apiService, database)
    }
}